package com.example.demo.util.encrypt.yc;

import java.io.IOException;
import java.nio.ByteBuffer;

import static com.example.demo.util.encrypt.yc.CryptoUtil.aesEncrypt;
import static com.example.demo.util.encrypt.yc.TicketSerializer.deserialize;
import static com.example.demo.util.encrypt.yc.TicketSerializer.serialize;

/**
 * @author 韩锐
 * @date 2019/6/5
 */
class FormsAuthentication {

    private static final int MAX_TICKET_LENGTH = 4096;
    private static final int SHA1_HASH_SIZE = 20;
    private static final int IV_LENGTH_DECRYPTION = 24;

    private static byte[] validationKey;
    private static byte[] decryptionKey;

    static {
        //生成新的key：https://www.allkeysgenerator.com/Random/ASP-Net-MachineKey-Generator.aspx
        validationKey = HexUtil.hexToBinary(Keys.VALIDATION_KEY);
        decryptionKey = HexUtil.hexToBinary(Keys.DECRYPTION_KEY);
    }

    private FormsAuthentication() {
    }

    /**
     * 解密并获取ticket（票据）
     * 将十六进制字符串转换成字节流
     * 将字节流一分为二，第一部分是加密的字节流，第二部分是Hash值
     * 根据加密的字节流生成Hash，并与第二部分的Hash比较，确认是否一致
     * 解密字节流
     * 将字节流转换成Ticket对象
     * @param encryptedTicket 已加密的ticket
     */
    static Ticket decrypt(String encryptedTicket) {
        if (encryptedTicket == null || encryptedTicket.length() == 0 || encryptedTicket.length() > MAX_TICKET_LENGTH) {
            throw new IllegalArgumentException("参数不合法:[" + encryptedTicket + "]");
        }

        //将十六进制字符串转换成字节流
        byte[] bBlob = null;
        if ((encryptedTicket.length() % 2) == 0) {
            bBlob = HexUtil.hexToBinary(encryptedTicket);
        }
        if (bBlob == null || bBlob.length < 1) {
            throw new IllegalArgumentException("将票据转换成字节数组失败:[" + encryptedTicket + "]");
        }

        //将字节流一分为二，第一部分是加密的字节流，第二部分是Hash值
        bBlob = getUnHashedData(bBlob);
        // At this point, buf contains only E(iv + m) if the signature check succeeded.
        byte[] paddedData = CryptoUtil.aesDecrypt(bBlob, decryptionKey);

        // DevDiv Bugs 137864: Strip IV from beginning of unencrypted data
        // strip off the first bytes that were random bits
        int ivLength = IV_LENGTH_DECRYPTION;
        int bDataLength = paddedData.length - ivLength;
        if (bDataLength < 0) {
            throw new IllegalArgumentException("Unable_to_validate_data");
        }

        byte[] bData = new byte[bDataLength];
        System.arraycopy(paddedData, ivLength, bData, 0, bDataLength);

        // 将字节流转换成Ticket对象

        return deserialize(bData);
    }

    static String encrypt(Ticket ticket) throws IOException {
        if (ticket == null) {
            throw new NullPointerException("ticket不能为null");
        }

        if (ticket.getName() == null || ticket.getUserData() == null || ticket.getCookiePath() == null) {
            return null;
        }

        // 将Ticket对象转换成字节流
        byte[] bBlob = serialize(ticket);
        if (bBlob == null) {
            return null;
        }

        byte[] iv = new byte[IV_LENGTH_DECRYPTION];
        ByteBuffer buf3 = ByteBuffer.allocate(iv.length + bBlob.length).put(iv).put(bBlob);
        
        // 使用AES算法加密字节流，此时bData = Enc(iv + buf)
        byte[] bData = aesEncrypt(buf3.array(), decryptionKey);

        // 根据加密的字节流生成Hash
        byte[] hmac = CryptoUtil.getHmacSha1Hash(bData, validationKey);
        
        // 将加密的字节流和Hash值合并
        byte[] bData2 = new byte[bData.length + hmac.length];
        System.arraycopy(bData, 0, bData2, 0, bData.length);
        System.arraycopy(hmac, 0, bData2, bData.length, hmac.length);
        bData = bData2;

        // 转成十六进制字符串，即为最终结果，此时 bData := Enc(iv + buf) + HMAC(Enc(iv + buf))
        return HexUtil.binaryToHex(bData);
    }

    private static byte[] getUnHashedData(byte[] bufHashed) {
        if (!verifyHashedData(bufHashed)) {
            throw new IllegalArgumentException("哈希校验失败");
        }

        byte[] buf2 = new byte[bufHashed.length - SHA1_HASH_SIZE];
        System.arraycopy(bufHashed, 0, buf2, 0, buf2.length);

        return buf2;
    }

    /**
     * 验证buf中的hash是否合法
     *
     * @param bufHashed 已经过hash处理的数据
     * @return true/false
     */
    private static boolean verifyHashedData(byte[] bufHashed) {
        int hashSize = SHA1_HASH_SIZE;

        // Step 1: Get the MAC: Last [HashSize] bytes
        if (bufHashed.length <= hashSize) {
            return false;
        }

        byte[] bufNeedUnHash = new byte[bufHashed.length - hashSize];
        System.arraycopy(bufHashed, 0, bufNeedUnHash, 0, bufHashed.length - hashSize);
        byte[] bMac = CryptoUtil.getHmacSha1Hash(bufNeedUnHash, validationKey);

        // Step 2: Make sure the MAC has expected length
        if (bMac == null || bMac.length != hashSize) {
            return false;
        }

        int lastPos = bufHashed.length - hashSize;

        for (int iter = 0; iter < hashSize; iter++) {
            if (bMac[iter] != bufHashed[lastPos + iter]) {
                return false;
            }
        }

        return true;
    }

}

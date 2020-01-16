package com.example.demo.util.encrypt.yc;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * @author 韩锐
 * @date 2019/6/5
 */
class CryptoUtil {

    private static final String SHA1_ALGORITHM = "HmacSHA1";
    private static final String AES_ALGORITHM = "AES";

    /**
     * 在C#中，aes使用的是PKCS7Padding算法填充，Java里没有这个
     * 但幸好在大部分情况下PKCS7Padding和PKCS5Padding是相通的
     * 所以这里直接用PKCS5Padding
     */
    private static final String AES_ALGORITHM_FULL = "AES/CBC/PKCS5Padding";

    /**
     * 生成HmacSha1签名
     *
     * @param buf 待签名的数据
     * @param key 密钥
     * @return HmacSha1签名
     */
    static byte[] getHmacSha1Hash(byte[] buf, byte[] key) {
        try {
            SecretKeySpec sKeySpec = new SecretKeySpec(key, SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(SHA1_ALGORITHM);
            mac.init(sKeySpec);
            return mac.doFinal(buf);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用aes算法解密
     *
     * @param buf 待解密的数据
     * @param key 密钥
     * @return 解密后的数据
     */
    static byte[] aesDecrypt(byte[] buf, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM_FULL);
            Key sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            AlgorithmParameters params = AlgorithmParameters.getInstance(AES_ALGORITHM);
            params.init(new IvParameterSpec(new byte[16]));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);
            return cipher.doFinal(buf);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    static byte[] aesEncrypt(byte[] buf, byte[] key) {
        try {
            //指定算法，模式，填充方法 创建一个Cipher实例
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM_FULL);

            byte[] iv = new byte[cipher.getBlockSize()];
            SecureRandom randomSecureRandom = new SecureRandom();
            randomSecureRandom.nextBytes(iv);

            Key sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            AlgorithmParameters params = AlgorithmParameters.getInstance(AES_ALGORITHM);
            params.init(new IvParameterSpec(iv));
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, params);

            return cipher.doFinal(buf);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

}

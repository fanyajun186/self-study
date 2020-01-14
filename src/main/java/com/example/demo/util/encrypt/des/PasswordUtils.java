package com.example.demo.util.encrypt.des;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

/**
 * =============================================================================
 * == @author xuyongzheng
 * == @date 6/24/2019
 * == @desc UserAccount 密码加密方法
 * =============================================================================
 **/
public class PasswordUtils {

    private static final String key = "auto@#$&";
    private static final byte[] ivs = new byte[]{
            0x12, 0x34, 0x56, 0x78,
            (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF
    };

    private static Logger logger = LoggerFactory.getLogger(PasswordUtils.class);

    /**
     * 解密
     *
     * @param input 加密后的内容
     * @return
     * @throws Exception
     */
    public static String decrypt(String input) {

        try {
            byte[] keyArr = key.getBytes(StandardCharsets.UTF_8);

            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            DESKeySpec desKeySpec = new DESKeySpec(keyArr);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

            IvParameterSpec iv = new IvParameterSpec(ivs);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            byte[] retByte = cipher.doFinal(Base64.decode(input));
            return new String(retByte, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            logger.error("password_decrypt_fail:[{}]", input, ex);
        }
        return null;
    }

    /**
     * 加密
     *
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(ivs);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] arr = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));

            return new String(Base64.encode(arr), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            logger.error("password_encrypt_fail:[" + input + "]", ex);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("168846"));
        System.out.println(decrypt("uz8D1om01+I="));
    }
}


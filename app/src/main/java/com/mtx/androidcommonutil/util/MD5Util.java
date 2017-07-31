package com.mtx.androidcommonutil.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 加密后的内容都很复杂，但是随着原被加密内容的增长，加密后字符串急剧增长
 * 单向加密算法：包含MD5和SHA,就是只能加密不能解密
 * Created by lishaoming on 16/2/27.
 */
public class MD5Util {
    private static BigInteger baseMod = new BigInteger("44215767532467932971471741161152512899095778854042634265037848310629766086249");
    private static BigInteger publicKey = new BigInteger("29477178354978621980981160774101675265783484927421444743425112544990446999947");
    private static BigInteger privateKey = new BigInteger("3");

    /**
     * 加密
     */
    public static String RSAEncrypt(String plainText) {
        try {
            return (new BigInteger(plainText.getBytes())).modPow(privateKey, baseMod).toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 解密
     */
    public static String RSADecrypt(String cryptText) {
        try {
            return new String((new BigInteger(cryptText)).modPow(publicKey, baseMod).toByteArray());
        } catch (Exception e) {
            return "";
        }
    }

    public static String md5(String account) {
        String append = "MJKHNGCDJENJFDNDJDHDasjflasjfl";
        String clearText = account + append;
        StringBuilder cipherText = new StringBuilder();
        byte[] clearBytes = clearText.getBytes();
        byte[] cipherBytes;
        try {
            cipherBytes = MessageDigest.getInstance("MD5").digest(clearBytes);
            for (int i = 0; i < cipherBytes.length; i++) {
                String hex = Integer.toHexString(cipherBytes[i] & 0xFF);
                if (hex.length() <= 1) {
                    hex = '0' + hex;
                }
                cipherText.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return cipherText.toString();
    }
}
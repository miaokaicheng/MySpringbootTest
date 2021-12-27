package com.mm.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: RsaUtil
 * @author: MKC
 * @date: 2021-11-25 18:00
 */
public class RsaUtil {
    private static Map<Integer, String> keyMap = new HashMap<>();

    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKyDX7FUAH0Ef10m+nMQdvZOH7oyE3C7f49DUh+0fGuYtp2XDzyv0L+aJX8YJrGjiLH9DZEbmajv1eMYU1aE5k6yn/QeqVqUdSLbWikt8lqDYKfTrNLinLTipPnHqDNerqM7S1tkOm5G34bR79bcvPVsCrf/zVH0UZxDsb9TMfpwIDAQAB";

    public static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIrINfsVQAfQR/XSb6cxB29k4fujITcLt/j0NSH7R8a5i2nZcPPK/Qv5olfxgmsaOIsf0NkRuZqO/V4xhTVoTmTrKf9B6pWpR1IttaKS3yWoNgp9Os0uKctOKk+ceoM16uoztLW2Q6bkbfhtHv1ty89WwKt//NUfRRnEOxv1Mx+nAgMBAAECgYEAic023HNDOv7zFQG43/hs+4zeXvCh4HiAWnCR+YX6xTYjWh0QbOKX1LYxHsWtzA8jKfGTfJZ/BxsINelR7a/+dfp/gl8lAQz92ZCcpWeRS4kGoD3YFMWPAwGl+XE9biYWVYumUefjCqZMDbbIX31kv11k8hGmdHFtTUCW+oj9ISECQQDPqUEk+o6NjUdTnSobVe4UvHMQ3r0vwoedqqVHg+oBh8GUS8Rw8f2isgg/2DXXMkcvLqmALiGKwpNxoNMJhn6fAkEAqxZhSsVd7Ge9xIVS5YUA3myw39Icay/NB7IMKk6+5OM0EhYIFknWmjrxXpexVq3wOT2WLqKwDkSDvApWA1Wp+QJAOfafdWfupHo7343t8+VfaDBV2e6iVhNxcUPxkG20wqqXEQK5GWGij2gsP03lcWTaU8QtkdbOjAHV0BC4916wNwJACSpmxfGy6XJZWUjnOwKYHFJoW2VPPnLOIiZovm9/jJWbeYiSoFcOVy7nNXEdAA7LetWQ0SjIE8uZ3x4So5UYSQJATtQz89kOEP9rjO6URpLtfrb8YNge9Aab76+rq/EgjZ7RG1sgAHJDpZc8lu7aIKLVVsDo8/sxtmFn4MFWAA495w==";

    public static final int MAX_ENCRYPT_BLOCK = 117;

    public static final int MAX_DECRYPT_BLOCK = 128;

    public static void genKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
        keyMap.put(0, publicKeyString);
        keyMap.put(1, privateKeyString);
    }

    public static String encrypt(String str, String publicKey) throws Exception {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    public static String splitEncrypt(String str, String publicKey) throws Exception {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, pubKey);
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int inputLength = bytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        int i = 0;
        while (inputLength - offset > 0) {
            byte[] cache;
            if (inputLength - offset > 117) {
                cache = cipher.doFinal(bytes, offset, 117);
            } else {
                cache = cipher.doFinal(bytes, offset, inputLength - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * 117;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return new String(Base64.encodeBase64(encryptedData), "utf-8");
    }

    public static String decrypt(String str, String privateKey) throws Exception {
        if (StringUtils.isEmpty(str))
            return str;
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey)KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    public static String splitDecrypt(String str, String privateKey) throws Exception {
        if (StringUtils.isEmpty(str))
            return str;
        byte[] bytes = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey)KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, priKey);
        int inputLength = bytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        int i = 0;
        while (inputLength - offset > 0) {
            byte[] cache;
            if (inputLength - offset > 128) {
                cache = cipher.doFinal(bytes, offset, 128);
            } else {
                cache = cipher.doFinal(bytes, offset, inputLength - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * 128;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        System.out.println(new SecureRandom());
    }
}


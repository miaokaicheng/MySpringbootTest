package com.mm.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Description MD5加密类
 * @Author MKC
 * @Date 2022/1/12
 */
@Slf4j
public class MD5Utils {
    private static final String SALT = "mkc";

    private static final String ALGORITHM_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String password) {
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
    }

    public static String encrypt(String username, String pswd) {
        return new SimpleHash(ALGORITHM_NAME, pswd, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
    }
    public static void main(String[] args) {
        log.info(MD5Utils.encrypt("test", "123456"));
    }
}

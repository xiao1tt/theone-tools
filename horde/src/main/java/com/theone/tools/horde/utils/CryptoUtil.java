package com.theone.tools.horde.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author chenxiaotong
 */
public class CryptoUtil {
    private static final AES AES = SecureUtil.aes();

    public static byte[] encrypt(String str) {
        return AES.encrypt(str);
    }

    public static byte[] decrypt(String code) {
        return AES.decrypt(code);
    }

    public static byte[] decrypt(byte[] code) {
        return AES.decrypt(code);
    }

    public static String encryptHex(String str) {
        return AES.encryptHex(str);
    }

    public static String decryptStr(String code) {
        return AES.decryptStr(code, CharsetUtil.CHARSET_UTF_8);
    }
}

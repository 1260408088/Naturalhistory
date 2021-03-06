package com.king.naturalhistory.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 密码中生成盐的方法
 */
public class CodecUtils {

    /**
     * 将密码加上盐后再经过md5加密
     *
     * @param data
     * @param salt
     * @return
     */
    public static String md5Hex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(data));
    }

    public static String shaHex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return DigestUtils.sha512Hex(salt + DigestUtils.sha512Hex(data));
    }

    /**
     * 生成盐
     *
     * @return
     */
    public static String generateSalt() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }
}

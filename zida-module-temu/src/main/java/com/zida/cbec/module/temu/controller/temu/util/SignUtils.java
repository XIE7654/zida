package com.zida.cbec.module.temu.controller.temu.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

public class SignUtils {
    /**
     * 生成签名
     * @param params 所有参数
     * @param appSecret 密钥
     * @return 签名字符串
     */
    public static String generateSign(Map<String, Object> params, String appSecret) {
        // 按 key 排序
        Map<String, Object> sortedParams = new TreeMap<>(params);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : sortedParams.entrySet()) {
            sb.append(entry.getKey()).append(entry.getValue() == null ? "" : entry.getValue());
        }
        String resultStr = sb.toString().replace(" ", "").replace("'", "\"");
        String signStr = appSecret + resultStr + appSecret;
        return md5(signStr).toUpperCase();
    }

    /**
     * MD5加密
     */
    public static String md5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法不可用", e);
        }
    }
}


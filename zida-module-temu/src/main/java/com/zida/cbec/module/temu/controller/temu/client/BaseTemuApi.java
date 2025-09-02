package com.zida.cbec.module.temu.controller.temu.client;// 创建新文件: BaseTemuApi.java

import com.zida.cbec.module.temu.controller.temu.util.SignUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Temu API 基础类，封装通用的API调用逻辑
 */
public abstract class BaseTemuApi {
    protected final TemuClient client;

    public BaseTemuApi(TemuClient client) {
        this.client = client;
    }

    /**
     * 构建基础请求参数
     * @param apiType API类型
     * @return 基础参数Map
     */
    protected Map<String, Object> buildBaseParams(String apiType) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", apiType);
        params.put("app_key", client.getAppKey());
        params.put("access_token", client.getAccessToken());
        params.put("timestamp", System.currentTimeMillis() / 1000);
        params.put("data_type", "JSON");
        return params;
    }

    /**
     * 添加签名并执行API调用
     * @param params 请求参数
     * @param responseType 响应类型
     * @param <T> 响应类型泛型
     * @return API响应结果
     */
    protected <T> T executeApiCall(Map<String, Object> params, Class<T> responseType) {
        // 生成签名
        String sign = SignUtils.generateSign(params, client.getAppSecret());
        params.put("sign", sign);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<T> response = client.getRestTemplate().exchange(
                client.getApiUrl(),
                org.springframework.http.HttpMethod.POST,
                entity,
                responseType
        );
        return response.getBody();
    }
}

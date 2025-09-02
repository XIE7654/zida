package com.zida.cbec.module.temu.controller.temu.client;


import com.zida.cbec.module.temu.controller.temu.api.Auth;
import com.zida.cbec.module.temu.controller.temu.api.Order;
import com.zida.cbec.module.temu.controller.temu.api.Product;
import com.zida.cbec.module.temu.framework.temu.config.TemuProperties;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;


public class TemuClient {
    public static final String DEFAULT_API_URL = "https://openapi-b-global.temu.com/openapi/router";

    @Getter
    private final String appKey;
    @Getter
    private final String appSecret;
    @Getter
    private final String accessToken;
    @Getter
    private final RestTemplate restTemplate;

    @Getter
    public final String apiUrl;

    // 添加 auth 属性
    public final Auth auth;

    public final Order order;

    public final Product product;
    /**
     * 使用配置属性创建客户端
     */
    public TemuClient(TemuProperties temuProperties, String accessToken) {
        this.appKey = temuProperties.getAppKey();
        this.appSecret = temuProperties.getAppSecret();
        this.accessToken = accessToken;
        System.out.println(appKey);
        System.out.println(appSecret);
        System.out.println(accessToken);
        System.out.println("===========");
        this.apiUrl = temuProperties.getApiUrl() != null ? temuProperties.getApiUrl() : DEFAULT_API_URL;
        this.restTemplate = new RestTemplate();
        // 初始化 auth 对象
        this.auth = new Auth(this);
        this.order = new Order(this);
        this.product = new Product(this);
    }
}

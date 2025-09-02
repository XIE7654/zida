package com.zida.cbec.module.ebay.framework.web.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "ebay.api")
public class EbayApiProperties {

    @NotEmpty(message = "ebay.api.authUrl 不能为空")
    private String authUrl;

    @NotEmpty(message = "ebay.api.apiUrl 不能为空")
    private String apiUrl;

    @NotEmpty(message = "ebay.api.appId 不能为空")
    private String appId;

    @NotEmpty(message = "ebay.api.devId 不能为空")
    private String devId;

    @NotEmpty(message = "ebay.api.certId 不能为空")
    private String certId;

    @NotEmpty(message = "ebay.api.ruName 不能为空")
    private String ruName;

    @NotEmpty(message = "ebay.api.scopes 不能为空")
    private List<String> scopes;

} 
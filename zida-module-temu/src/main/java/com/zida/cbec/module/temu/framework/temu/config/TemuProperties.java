package com.zida.cbec.module.temu.framework.temu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "temu.api")
public class TemuProperties {
    private String apiUrl;
    private String appKey;
    private String appSecret;
}

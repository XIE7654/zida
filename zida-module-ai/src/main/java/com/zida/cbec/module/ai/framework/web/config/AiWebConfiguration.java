package com.zida.cbec.module.ai.framework.web.config;

import com.zida.cbec.framework.swagger.config.ZidaSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ai 模块的 web 组件的 Configuration
 *
 * @author 自达源码
 */
@Configuration(proxyBeanMethods = false)
public class AiWebConfiguration {

    /**
     * ai 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi aiGroupedOpenApi() {
        return ZidaSwaggerAutoConfiguration.buildGroupedOpenApi("ai");
    }

}

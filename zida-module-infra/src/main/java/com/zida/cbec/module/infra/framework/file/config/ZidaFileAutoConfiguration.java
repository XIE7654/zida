package com.zida.cbec.module.infra.framework.file.config;

import com.zida.cbec.module.infra.framework.file.core.client.FileClientFactory;
import com.zida.cbec.module.infra.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件配置类
 *
 * @author 自达源码
 */
@Configuration(proxyBeanMethods = false)
public class ZidaFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}

package com.zida.cbec.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 * @author 自达源码
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${zida.info.base-package}
@SpringBootApplication(scanBasePackages = {"${zida.info.base-package}.server", "${zida.info.base-package}.module"})
public class ZidaServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZidaServerApplication.class, args);
//        new SpringApplicationBuilder(ZidaServerApplication.class)
//                .applicationStartup(new BufferingApplicationStartup(20480))
//                .run(args);

    }

}

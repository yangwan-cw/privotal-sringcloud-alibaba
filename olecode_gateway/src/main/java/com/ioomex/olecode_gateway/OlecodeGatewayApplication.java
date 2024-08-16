package com.ioomex.olecode_gateway;

import com.ioomex.common.app.starter.ApplicationRunStarter;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient
public class OlecodeGatewayApplication {

    public static void main(String[] args) {
        // 创建 SpringApplicationBuilder 对象并进行相关设置
        SpringApplicationBuilder builder = new SpringApplicationBuilder(OlecodeGatewayApplication.class)
          .main(SpringVersion.class)
          .bannerMode(Banner.Mode.CONSOLE);

        // 运行 Spring 应用
        ConfigurableApplicationContext run = builder.run(args);

        // 获取环境对象
        Environment env = run.getEnvironment();
        ApplicationRunStarter.logApplicationStartup(env);
    }

}

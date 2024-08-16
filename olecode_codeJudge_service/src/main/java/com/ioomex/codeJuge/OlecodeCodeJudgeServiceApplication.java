package com.ioomex.codeJuge;

import com.ioomex.codeJuge.app.judge.rabbitmq.InitMq;
import com.ioomex.common.app.config.MyBatisPlusConfig;
import com.ioomex.common.app.exception.GlobalExceptionHandler;
import com.ioomex.common.app.starter.ApplicationRunStarter;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ioomex.service.client.service"})
@Import(value = {GlobalExceptionHandler.class, MyBatisPlusConfig.class})
public class OlecodeCodeJudgeServiceApplication {

    public static void main(String[] args) {
        // 创建 SpringApplicationBuilder 对象并进行相关设置
        SpringApplicationBuilder builder = new SpringApplicationBuilder(OlecodeCodeJudgeServiceApplication.class)
          .main(SpringVersion.class)
          .bannerMode(Banner.Mode.CONSOLE);

        // 运行 Spring 应用
        ConfigurableApplicationContext run = builder.run(args);

        // 获取环境对象
        Environment env = run.getEnvironment();
        ApplicationRunStarter.logApplicationStartup(env);
    }

}

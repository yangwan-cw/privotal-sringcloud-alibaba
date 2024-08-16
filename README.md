# olecode_springcloud_alibaba

## 项目概述

`olecode_springcloud_alibaba` 是一个基于 Spring Boot 和 Spring Cloud Alibaba 构建的微服务项目。

## 项目结构

项目包含以下模块：

- `olecode_common`
- `olecode_module`
- `olecode_codeJudge_service`
- `olecode_gateway`
- `olecode_question_service`
- `olecode_service_client`
- `olecode_user_service`

## 配置

### Maven 属性

- **Java 版本**: 11
- **项目编码**: UTF-8
- **Spring Boot 版本**: 2.7.5
- **Spring Cloud Alibaba 版本**: 2021.0.5.0
- **MyBatis 版本**: 2.2.2
- **MySQL 连接器 版本**: 8.0.30
- **Arthas 版本**: 3.6.7
- **Spring Cloud 依赖管理 版本**: 2021.0.5
- **Maven 编译插件 版本**: 3.8.1
- **Spring Boot Maven 插件 版本**: 2.7.5

### 依赖

| 依赖名称                              | 组 ID                | 工件 ID                             | 版本                                | 范围    |
|-------------------------------------|----------------------|------------------------------------|-------------------------------------|---------|
| Spring Boot Web 启动器                | `org.springframework.boot` | `spring-boot-starter-web`            | `${spring-boot.version}`              | 默认    |
| Spring Cloud Alibaba Sentinel Gateway | `com.alibaba.cloud`  | `spring-cloud-alibaba-sentinel-gateway` | `${spring-cloud-alibaba.version}`     | 默认    |
| Spring Cloud Alibaba Nacos Discovery  | `com.alibaba.cloud`  | `spring-cloud-starter-alibaba-nacos-discovery` | `${spring-cloud-alibaba.version}`     | 默认    |
| Spring Cloud Alibaba Sentinel         | `com.alibaba.cloud`  | `spring-cloud-starter-alibaba-sentinel` | `${spring-cloud-alibaba.version}`     | 默认    |
| MyBatis Spring Boot 启动器             | `org.mybatis.spring.boot` | `mybatis-spring-boot-starter`         | `${mybatis.version}`                  | 默认    |
| Spring Boot DevTools                  | `org.springframework.boot` | `spring-boot-devtools`               | N/A                                 | runtime |
| MySQL 连接器                           | `com.mysql`          | `mysql-connector-j`                  | N/A                                 | runtime |
| Arthas Spring Boot Starter            | `com.taobao.arthas`  | `arthas-spring-boot-starter`         | `${arthas.version}`                  | runtime |
| Lombok                                | `org.projectlombok`  | `lombok`                             | N/A                                 | 默认    |
| Spring Boot 测试启动器                | `org.springframework.boot` | `spring-boot-starter-test`           | N/A                                 | test    |
| Spring Cloud 依赖管理                  | `org.springframework.cloud` | `spring-cloud-dependencies`          | `${spring-cloud-dependencies.version}` | import  |

### 依赖管理

| 依赖名称                              | 组 ID                | 工件 ID                             | 版本                                | 类型    | 范围    |
|-------------------------------------|----------------------|------------------------------------|-------------------------------------|---------|---------|
| Spring Boot 依赖管理                  | `org.springframework.boot` | `spring-boot-dependencies`           | `${spring-boot.version}`              | pom     | import  |
| Spring Cloud Alibaba 依赖管理         | `com.alibaba.cloud`  | `spring-cloud-alibaba-dependencies` | `${spring-cloud-alibaba.version}`     | pom     | import  |

## 构建

### Maven 编译插件

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>${maven-compiler-plugin.version}</version>
    <configuration>
        <source>${java.version}</source>
        <target>${java.version}</target>
        <encoding>${project.build.sourceEncoding}</encoding>
    </configuration>
</plugin>



### 项目部署工具
1. github action
2. github hub
3. gitlab
4. jenkins
5. docker
6. docker-compose
7. linux


```
测试部署123
```

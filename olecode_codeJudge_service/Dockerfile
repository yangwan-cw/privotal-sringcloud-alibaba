# 使用官方 OpenJDK 镜像作为运行环境
FROM openjdk:11-jre-slim

# 设置工作目录
WORKDIR /app

ADD target/olecode_codeJudge_service-0.0.1-SNAPSHOT.jar .

EXPOSE 8101

ENTRYPOINT ["java","-jar","/app/olecode_codeJudge_service-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
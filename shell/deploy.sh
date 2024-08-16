#!/bin/bash

# 定义项目目录路径
PROJECT_DIR="/opt/app/olecode_alibaba_dev"  # 根据实际路径修改

# 定义 Docker Compose 文件的名称
COMPOSE_FILES=("docker-compose.env.yml" "docker-compose.service.yml")
CONTAINER_NAMES_BUSINESS=("olecode-question-service" "olecode-judge-service" "olecode-user-service" "olecode-gateway-service")
CONTAINER_NAMES_ENV=("olecode-redis" "olecode-rabbitmq" "olecode-nacos" "olecode-mysql")
IMAGE_NAMES=("olecode_springcloud_alibaba_dev-olecode-gateway-service:latest" "olecode_springcloud_alibaba_dev-olecode-user-service:latest" "olecode_springcloud_alibaba_dev-olecode-question-service:latest" "olecode_springcloud_alibaba_dev-olecode-backend-judge-service:latest")

cd ${PROJECT_DIR} || { echo "无法切换到项目目录: ${PROJECT_DIR}"; exit 1; }

# 启动环境依赖服务，如果不存在则创建
start_environment_services() {
    for FILE in "${COMPOSE_FILES[@]}"; do
        if [[ "${FILE}" == *"env"* ]]; then
            echo "从 ${FILE} 启动环境服务"
            docker-compose -f ${FILE} up -d
        fi
    done
}

# 停止并删除指定的容器
cleanup_services() {
    for CONTAINER_NAME in "${CONTAINER_NAMES_BUSINESS[@]}"; do
        if docker ps -a --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
            echo "停止并删除业务模块容器: ${CONTAINER_NAME}"
            docker stop ${CONTAINER_NAME}
            docker rm ${CONTAINER_NAME}
        fi
    done
}

# 删除指定的镜像
cleanup_images() {
    for IMAGE_NAME in "${IMAGE_NAMES[@]}"; do
        if docker images --format '{{.Repository}}:{{.Tag}}' | grep -q "^${IMAGE_NAME}$"; then
            echo "删除镜像: ${IMAGE_NAME}"
            docker rmi ${IMAGE_NAME}
        fi
    done
}

# 启动业务模块服务
start_services() {
    for FILE in "${COMPOSE_FILES[@]}"; do
        if [[ "${FILE}" == *"service"* ]]; then
            echo "从 ${FILE} 启动业务模块服务"
            docker-compose -f ${FILE} up -d
        fi
    done
}

# 主脚本执行
echo "开始部署脚本"

# 启动环境依赖服务（如果需要）
start_environment_services

# 清理业务模块容器和镜像
cleanup_services
cleanup_images

# 启动业务模块服务
start_services

echo "部署脚本完成"

#!/bin/bash

# Maven 빌드
function maven_build() {
    echo "Running Maven clean and package..."
    mvn clean package
    if [ $? -ne 0 ]; then
        echo "Maven build failed. Exiting..."
        exit 1
    fi

    # JAR 파일 확인
    JAR_FILE=$(ls target/*.jar 2>/dev/null)
    if [ -z "$JAR_FILE" ]; then
        echo "JAR file not found in target directory. Exiting..."
        exit 1
    fi
    echo "JAR file located: $JAR_FILE"
}

# Docker 컨테이너 빌드 및 실행
function start_containers() {
    echo "Starting Docker containers..."
    docker-compose down
    docker-compose build
    docker-compose up -d
}

# 전체 재시작
function restart_all() {
    maven_build
    start_containers
}

# 옵션 처리
case "$1" in
    --restart)
        restart_all
        ;;
    *)
        echo "Invalid option. Use --restart."
        exit 1
        ;;
esac

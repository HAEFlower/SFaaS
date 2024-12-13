#!/bin/bash

# Maven 빌드
mvn clean package

# Maven에서 artifactId와 version 가져오기
ARTIFACT_ID=$(mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout)
VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# 최종 JAR 이름
JAR_NAME="${ARTIFACT_ID}-${VERSION}.jar"

# Docker 빌드 및 실행
function build_and_restart() {
    echo "Stopping and removing existing containers..."
    docker-compose down

    echo "Building Docker image..."
    docker build --build-arg JAR_FILE=${JAR_NAME} -t my-spring-app .

    echo "Starting containers..."
    docker-compose up --build
}

function start_containers() {
    echo "Starting containers..."
    docker-compose up -d
}

function stop_containers() {
    echo "Stopping containers..."
    docker-compose down
}

# 사용법 안내
function usage() {
    echo "Usage: $0 [--restart | --start | --stop]"
    echo "Options:"
    echo "  --restart   Stop and rebuild before restarting containers"
    echo "  --start     Start containers without rebuilding"
    echo "  --stop      Stop running containers"
    exit 1
}

# 옵션 처리
case "$1" in
    --restart)
        build_and_restart
        ;;
    --start)
        start_containers
        ;;
    --stop)
        stop_containers
        ;;
    *)
        echo "Invalid option. Use --restart, --start, or --stop."
        usage
        ;;
esac

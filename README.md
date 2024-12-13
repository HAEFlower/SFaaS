# SFaaS Project - BSA Monitoring API

## 소개
이 프로젝트는 Docker를 사용하여 Nginx, MySQL, 그리고 Spring Boot 기반의 간단한 REST API를 제공합니다. 테스트를 위해 `/api/hello` 엔드포인트를 제공합니다.

## 실행 방법

### 1. Docker 기반 실행

1. **Maven 빌드**:
   ```bash
   mvn clean package
   ```

2. **Docker Compose 실행**:
   ```bash
   docker-compose up --build
   ```

3. 애플리케이션이 실행되면 아래 상태를 확인하세요:
   - Nginx: `localhost:80`에서 리버스 프록시 역할 수행
   - Spring Boot: Nginx를 통해 `localhost:80/api/hello`로 접근 가능
   - MySQL: 내부적으로 컨테이너 간 연결

### 2. API 테스트

테스트 엔드포인트:
- **URL**: `GET http://localhost/api/hello`

cURL 사용:
```bash
curl -X GET http://localhost/api/hello
```

예상 응답:
```plaintext
Hello, World! MySQL connection successful.
```

## 환경 설정

### 1. MySQL 기본 설정
`docker-compose.yml`에서 MySQL 컨테이너의 환경 변수를 설정합니다:

```yaml
environment:
  MYSQL_ROOT_PASSWORD: root
  MYSQL_DATABASE: testdb
  MYSQL_USER: testuser
  MYSQL_PASSWORD: testpass
```

### 2. Spring Boot 설정
Spring Boot는 `src/main/resources/application.properties` 파일에서 데이터베이스 정보를 설정합니다:

```properties
spring.datasource.url=jdbc:mysql://mysql:3306/testdb
spring.datasource.username=testuser
spring.datasource.password=testpass
```

## Nginx 역할 및 설정

Nginx는 클라이언트와 Spring Boot 애플리케이션 간 리버스 프록시 역할을 합니다.

- **Nginx 설정 파일** (`nginx/default.conf`):
```nginx
server {
    listen 80;
    location / {
        proxy_pass http://app:8081;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

## 컨테이너 상태 확인 및 종료

### 컨테이너 상태 확인
실행 중인 컨테이너를 확인하려면 다음 명령을 사용하세요:
```bash
docker ps
```

### 컨테이너 종료
Docker Compose로 실행된 컨테이너를 종료하려면 다음 명령을 사용하세요:
```bash
docker-compose down
```

## 문제 해결

### 빌드 오류
- Maven 설치 여부 확인:
  ```bash
  mvn -version
  ```
- `target/` 디렉토리 내에 빌드된 JAR 파일이 생성되었는지 확인:
  ```bash
  ls target/
  ```

### 포트 충돌
- `localhost:80` 또는 `localhost:3306` 포트가 이미 사용 중인지 확인 후 해결

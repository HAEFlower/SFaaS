# SFaaS
SFaaS Project

# BSA Monitoring API

## 소개
이 프로젝트는 Spring Boot를 사용하여 작성된 간단한 REST API입니다.  
테스트를 위해 `/api/hello` 엔드포인트를 제공합니다.

---

## 실행 방법
### 1. 프로젝트 실행
1. **Maven 빌드**:
    ```bash
    mvn clean install
    ```

2. **Spring Boot 실행**:
    ```bash
    mvn spring-boot:run
    ```

3. 애플리케이션이 실행되면 아래와 같은 로그를 확인하세요:
    ```
    Tomcat started on port(s): 8080 (http)
    ```

---

### 2. API 테스트
**테스트 엔드포인트**:
- `GET /api/hello`

#### cURL 사용
```bash
curl -X GET http://localhost:8080/api/hello


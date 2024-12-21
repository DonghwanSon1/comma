# 1. 기본 이미지 선택
FROM openjdk:11-jdk AS build

# 2. 작업 디렉토리 설정
WORKDIR /spring-boot

# 3. Gradle Wrapper 및 Gradle 설정 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# 4. 소스 코드 복사
COPY src src

# 5. 실행 권한 부여
RUN chmod +x ./gradlew

# 6. Gradle 빌드 수행
RUN ./gradlew clean build

# 7. JAR 파일을 앱에 복사
FROM openjdk:11-jdk

# 8. 작업 디렉토리 설정
WORKDIR /spring-boot

# 9. 빌드된 JAR 파일 복사
COPY --from=build /spring-boot/build/libs/*.jar app.jar

## 10. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/spring-boot/app.jar"]

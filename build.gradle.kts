plugins {
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "2.7.18"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("kapt") version "1.8.0"
	id ("org.flywaydb.flyway") version "8.5.13"
	kotlin("plugin.allopen") version "1.5.10"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(11)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")

	// QueryDSL
	implementation("com.querydsl:querydsl-jpa:5.0.0")
	kapt("com.querydsl:querydsl-apt:5.0.0:jpa")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-ui:1.6.11")

	// Flyway
	implementation ("org.flywaydb:flyway-core")
	implementation ("org.flywaydb:flyway-mysql")

	// Spring Security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

tasks.withType<Test> {
	useJUnitPlatform()
}


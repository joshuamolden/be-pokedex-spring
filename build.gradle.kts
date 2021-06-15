import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.liquibase.gradle") version "2.0.4"
	kotlin("jvm") version "1.5.10"
	kotlin("plugin.spring") version "1.5.10"
	kotlin("plugin.jpa") version "1.5.10"
}

group = "com.bushelpowered.pokedex"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.liquibase:liquibase-core")
	implementation("org.mariadb.jdbc:mariadb-java-client:2.7.1")
	liquibaseRuntime("ch.qos.logback:logback-classic:1.2.3")
	liquibaseRuntime("ch.qos.logback:logback-core:1.2.3")
	liquibaseRuntime("org.mariadb.jdbc:mariadb-java-client:2.7.1")
	liquibaseRuntime("org.liquibase:liquibase-core:3.5.1")
	liquibaseRuntime(sourceSets.getByName("main").output)
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
this.loadProperties()

fun loadProperties() {
	val environment = if (hasProperty("env"))
		property("env").toString()
	else "local"

	val configFile = when (environment) {
		"dev", "qa", "uat" -> file("config.remote.groovy")
		else -> file("config.groovy")
	}

	project.extra["config"] = groovy.util.ConfigSlurper(environment).parse(configFile.readText())
}

liquibase {
	val config = (rootProject.extra["config"] as groovy.util.ConfigObject).flatten()

	activities.register("main") {
		this.arguments = mapOf(
				"logLevel" to "info",
				"changeLogFile" to config["spring.datasource.changelog"],
				"url" to config["spring.datasource.url"],
				"username" to config["spring.datasource.username"],
				"password" to config["spring.datasource.password"]
		)
	}
}


tasks.withType<Test> {
	useJUnitPlatform()
}

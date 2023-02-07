plugins {
    java
    idea
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "su.mhl"
version = "2023.01.12"

repositories {
    mavenCentral()
}
sourceSets {
    main {
        java {
            srcDir { "src/main/java" }
        }
        resources {
            srcDir { "src/main/resources" }
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.leangen.graphql:graphql-spqr-spring-boot-starter:0.0.7")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
tasks.withType<Copy>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

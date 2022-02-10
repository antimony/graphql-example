plugins {
    java
    idea
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "su.mhl"
version = "2021.05.14"

repositories {
    jcenter()
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
    implementation("io.leangen.graphql:graphql-spqr-spring-boot-starter:0.0.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

plugins {
    id("java")
    id("io.freefair.lombok") version "9.1.0"
}

group = "com.sqherd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("io.freefair.lombok:io.freefair.lombok.gradle.plugin:9.1.0")
}

tasks.test {
    useJUnitPlatform()
}
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
    testImplementation("org.assertj:assertj-core:3.27.6")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("io.freefair.lombok:io.freefair.lombok.gradle.plugin:9.1.0")
    implementation("com.sqherd:input-parser")
}

tasks.test {
    useJUnitPlatform()
}

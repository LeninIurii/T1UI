plugins {
    id("java")
    id ("io.qameta.allure") version "2.11.0"

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
tasks.withType<Test> {
    systemProperty("file.encoding", "UTF-8")
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
// https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
    implementation("io.github.bonigarcia:webdrivermanager:latest.release")

//    // https://mvnrepository.com/artifact/com.codeborne/selenide
    implementation("com.codeborne:selenide:6.1.0")

    // https://mvnrepository.com/artifact/io.qameta.allure/allure-testng
    implementation("io.qameta.allure:allure-testng:latest.release")

// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    implementation("org.junit.jupiter:junit-jupiter-api:5.8.2")

// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")

    // https://mvnrepository.com/artifact/io.qameta.allure/allure-junit5
    testImplementation("io.qameta.allure:allure-junit5:latest.release")


    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:latest.release")

// https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    implementation("org.seleniumhq.selenium:selenium-java:latest.release")

    // https://mvnrepository.com/artifact/log4j/log4j
    implementation("log4j:log4j:1.2.16")

    // https://mvnrepository.com/artifact/io.qameta.allure/allure-commandline
    implementation("io.qameta.allure:allure-commandline:2.9.0")

    // https://mvnrepository.com/artifact/org.aspectj/aspectjweaver
    testImplementation("org.aspectj:aspectjweaver:1.9.22")

// https://mvnrepository.com/artifact/io.qameta.allure/allure-selenide
    implementation("io.qameta.allure:allure-selenide:2.26.0")

// https://mvnrepository.com/artifact/org.testng/testng
    implementation("org.testng:testng:7.10.0")

}

tasks.test {
    useJUnitPlatform()
}
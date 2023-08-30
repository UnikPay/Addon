version = "0.1.0"

plugins {
    id("java-library")
}

dependencies {
    api(project(":api"))
    maven(mavenCentral(), "io.socket:socket.io-client:2.1.0")
    maven(mavenCentral(), "io.socket:engine.io-client:2.1.0")
    maven(mavenCentral(), "org.json:json:20230618")
    maven(mavenCentral(), "com.squareup.okhttp3:okhttp:4.11.0")
    maven(mavenCentral(), "org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    maven(mavenCentral(), "com.squareup.okio:okio-jvm:3.5.0")
    maven("https://jitpack.io", "com.github.UnikPay.UnikPay:unikpay-api:1.0.10")
    // If you want to use external libraries, you can do that here.
    // The dependencies that are specified here are loaded into your project but will also
    // automatically be downloaded by labymod, but only if the repository is public.
    // If it is private, you have to add and compile the dependency manually.
    // You have to specify the repository, there are getters for maven central and sonatype, every
    // other repository has to be specified with their url. Example:
    // maven(mavenCentral(), "org.apache.httpcomponents:httpclient:4.5.13")
}

labyModProcessor {
    referenceType = net.labymod.gradle.core.processor.ReferenceType.DEFAULT
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

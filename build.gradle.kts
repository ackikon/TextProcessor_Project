plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("plugin.serialization") version "2.3.20"
    //id("com.google.devtools.ksp") version "2.3.4" // Or latest version of KSP
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
    //implementation("com.squareup.moshi:moshi-kotlin:1.15.2")
    //ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.2")

}

tasks.test {
    useJUnitPlatform()
}
plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.bladehunt"
version = "1.0-SNAPSHOT"

repositories {
    maven(url = uri("https://jitpack.io"))
    mavenCentral()
}

dependencies {
    implementation("com.github.Minestom.Minestom:Minestom:-SNAPSHOT")
    implementation(project(mapOf("path" to ":core")))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
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
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
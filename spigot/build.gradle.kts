plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.bladehunt"
version = "1.0-SNAPSHOT"

repositories {
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    mavenCentral()
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
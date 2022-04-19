import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "de.bixilon.kotlin-glm"
version = "0.9.9.1-4"

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("kotlin.graphics:unsigned:3.3.3")
    implementation("kotlin.graphics:kool:0.9.1")

    compileOnly(platform("org.lwjgl:lwjgl-bom:3.3.1"))
    compileOnly("org.lwjgl", "lwjgl")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

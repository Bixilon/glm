import kx.LwjglModules.*
import kx.lwjglImplementation

plugins {
    val build = "0.4.0"
    id("kx.kotlin.11") version build
    id("kx.lwjgl") version build
    id("kx.dokka") version build
    java
    `maven-publish`
}

group = "com.github.kotlin.graphics"

repositories {
    maven("https://repo.repsy.io/mvn/elect/kx")
    maven("https://jitpack.io")
}

dependencies {

    implementation(platform("kx.platform:source:0.0.4"))

    val kx = "com.github.kotlin-graphics"
    implementation("$kx:kotlin-unsigned:14a46b97")
    implementation("$kx:kool:52895c39")

//    implementation("kx.platform:source:0.0.3")

    lwjglImplementation(glfw, jemalloc, openal, opengl, stb)
}
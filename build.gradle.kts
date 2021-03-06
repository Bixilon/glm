import kx.LwjglModules.*
import kx.KxProject.*
import kx.kxImplementation
import kx.lwjglImplementation

plugins {
    val build = "0.5.5"
    id("kx.kotlin.11") version build
    id("kx.lwjgl") version build
    id("kx.dokka") version build
    java
    `maven-publish`
}

group = "com.github.kotlin.graphics"
version = "0.9.9.1-4"

repositories {
    maven("https://repo.repsy.io/mvn/elect/kx")
    maven("https://jitpack.io")
}

dependencies {

    kxImplementation(unsigned, kool)

    lwjglImplementation(glfw, jemalloc, openal, opengl, stb)
}
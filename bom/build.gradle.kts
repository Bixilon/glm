plugins {
    `java-platform`
    `maven-publish`
}

repositories {
    maven("https://jitpack.io")
}

val kx = "com.github.kotlin-graphics"
val unsignedVersion: String by project
val koolVersion: String by project

dependencies {
    constraints {
        api("$kx:kotlin-unsigned:$unsignedVersion")
        api("$kx:kool:$koolVersion")
    }
}

publishing.publications.create<MavenPublication>("mavenJava") {
    from(components["javaPlatform"])
}

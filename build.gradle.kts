import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.publish.PublishingExtension

plugins {
    kotlin("jvm") version "1.6.20"
    `java-library`
    `maven-publish`
    signing
}

group = "de.bixilon.kotlin-glm"
version = "0.9.9.1-4"

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.2.3")
    implementation("kotlin.graphics:unsigned:3.3.3")
    implementation("kotlin.graphics:kool:0.9.1")

    compileOnly(platform("org.lwjgl:lwjgl-bom:3.3.1"))
    compileOnly("org.lwjgl", "lwjgl")
}

tasks.test {
    useJUnitPlatform()
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

java {
    withJavadocJar()
    withSourcesJar()
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "de.bixilon"
            artifactId = "kotlin-glm"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("kotlin-glm")
                description.set("Kotlin port of OpenGL Mathematics (GLM)")
                url.set("https://gitlab.bixilon.de/bixilon/kotlin-glm")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://choosealicense.com/licenses/mit/")
                    }
                }
                developers {
                    developer {
                        id.set("bixilon")
                        name.set("Moritz Zwerger")
                        email.set("bixilon@bixilon.de")
                    }
                }
                scm {
                    connection.set("scm:git:https://gitlab.bixilon.de/bixilon/kotlin-glm.git")
                    developerConnection.set("scm:git:ssh://git@gitlab.bixilon.de:222/bixilon/kotlin-glm.git")
                    url.set("https://gitlab.bixilon.de/bixilon/kotlin-glm")
                }
            }
        }
    }
    repositories {
        maven {
            credentials {
                username = project.properties["NEXUS_USERNAME"].toString()
                password = project.properties["NEXUS_PASSWORD"].toString()
            }

            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
        }
    }
}
signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

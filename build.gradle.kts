import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0-Beta"
    `maven-publish`
    signing
}

group = "de.bixilon.kotlin-glm"
version = "0.9.9.1-7"

repositories {
    mavenCentral()
    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    compileOnly("kotlin.graphics:unsigned:3.3.3")
    testImplementation("kotlin.graphics:unsigned:3.3.3")

    compileOnly(platform("org.lwjgl:lwjgl-bom:3.3.2"))
    compileOnly("org.lwjgl", "lwjgl")

    implementation("de.bixilon:kotlin-kool:0.9.3-1")
}

tasks.test {
    useJUnitPlatform()
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.languageVersion = "2.0"
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
            // url = uri("https://s01.oss.sonatype.org/content/repositories/releases/")
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
    // kotlinOptions.languageVersion = "2.0"
}

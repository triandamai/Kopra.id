import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}
// Required since Gradle 4.10+.
repositories {
    jcenter()
    google()
    maven(url = "https://maven.google.com")
    maven ( url ="https://jitpack.io" )
    mavenCentral()
}
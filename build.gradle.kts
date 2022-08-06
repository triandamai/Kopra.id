/**
* Copyright Trian Damai 2022 triandamai@gmail.com
*
* Top-level build file where you can add configuration options common to all sub-projects/modules.
**/

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.1")
        // Check that you have the Google services Gradle plugin v4.3.2 or later
        // (if not, add it).
        classpath("com.google.gms:google-services:4.3.13")

        // Add the Crashlytics Gradle plugin
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.1")

    }
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

plugins{
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
    kotlin("android") version "1.6.10" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.10" apply false
}

tasks.create<Delete>("cleanRp"){
    delete(
        rootProject.buildDir
    )
}

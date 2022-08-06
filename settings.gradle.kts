/**
 * Copyright Trian Damai 2022 triandamai@gmail.com
 *
 * */
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        mavenCentral()

    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")
    }
}

rootProject.buildFileName = "build.gradle.kts"
rootProject.name = "Kopra"
include(":mainApp")
include(":data")
//include(":domain")
//include(":common")
include(":component")

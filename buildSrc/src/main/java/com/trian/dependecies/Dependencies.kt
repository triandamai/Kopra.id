package com.trian.dependecies

object ApplicationId{
    const val id = "com.trian.app"
}

object Modules{
    const val data = ":data"
    const val domain = ":domain"
    const val common = ":common"
    const val presentation = ":presentation"
    const val app = ":app"
    const val component = ":component"

    //features
    const val microlife = ":features:microlife"
    const val smartwatch = ":features:smartwatch"
    const val oximeter_ring = ":features:oximeter_ring"
    const val bmi = ":features:bmi"
    const val waist = ":features:waist"
    const val camera = ":features:camera"
}

object Releases{
    const val versionCode =1
    const val versionName="1.0"
}

/**
 * Arranged alphabetically
 */
object Versions {
    const val kotlinVersion = "1.5.21"
    const val compose_version = "1.0.1"
    const val safeArgs = "2.2.2"
    const val compileSdkVersion = 31
    const val buildTool = "28"
    const val minSdk = 22
    const val targetSdk = 30
    const val hiltGradlePluginVersion = "2.38.1"
    const val hiltJetpackVersion = "1.0.0-alpha02"
    const val hiltVersion = "2.38.1"
    const val roomVersion = "2.2.5"
    const val lifecycle = "2.2.0"
    const val dagger = "2.24"
}
object Libs{

    object GradlePlugin{
      const val  androidGradlePlugin = "com.android.tools.build:gradle:7.0.1"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltGradlePluginVersion}"
    }

    object AndroidX{
        private const val ktx_core = "1.6.0"
        private const val appcompat = "1.3.1"
        private const val material_version = "1.1.0"
        private const val test_navigation = "2.4.0-alpha07"

        object Activity{
            const val activityCompose =  "androidx.activity:activity-compose:1.3.1"
        }
        const val appCompat =  "androidx.appcompat:appcompat:$appcompat"
        const val material ="com.google.android.material:material:$material_version"
        object Compose{
            const val Ui = "androidx.compose.ui:ui:${Versions.compose_version}"
            const val UiMaterial ="androidx.compose.material:material:${Versions.compose_version}"
            const val UiPreview ="androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
            const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
            const val RuntimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose_version}"
            const val Navigation = "androidx.navigation:navigation-compose:$test_navigation"
            const val Foundation = "androidx.compose.foundation:foundation:${Versions.compose_version}"
            const val Icons = "androidx.compose.material:material-icons-core:${Versions.compose_version}"
            const val IconsExtended= "androidx.compose.material:material-icons-extended:${Versions.compose_version}"
            const val AccompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:0.14.0"
            const val AccompanistPager = "com.google.accompanist:accompanist-pager:0.15.0"
            object Test{
                const val uiTest = "androidx.compose.ui:ui-test:${Versions.compose_version}"
                const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
                const val testJunit= "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
                const val uiTooling ="androidx.compose.ui:ui-tooling:${Versions.compose_version}"
                const val testManifest =  "androidx.compose.ui:ui-test-manifest:${Versions.compose_version}"
                const val testNavigation = "androidx.navigation:navigation-testing:$test_navigation"
            }
        }


        const val coreKtx= "androidx.core:core-ktx:$ktx_core"



       object Room{
           // ROOM
           const val runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
           const val Compiler = "androidx.room:room-compiler:${Versions.roomVersion}"
           const val ktx = "androidx.room:room-ktx:${Versions.roomVersion}"
       }

        // optional - Kotlin Extensions and Coroutines support for Room

        object LifeCycle{
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
            const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            // To integrate with ViewModel
            private const val  lifecycle_runtime_ktx = "2.3.1"
            const val runtimeKtx= "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_runtime_ktx"
        }
        object Test{
            private const val version = "1.4.0"
            private const val espresso_core = "3.4.0"
            private const val android_testRunner = "1.1.0"
            private const val junitTest = "1.0.0"

            const val core = "androidx.test:core:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"
            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }
            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"


            const val coreTesting ="androidx.arch.core:core-testing:2.1.0"


            // TODO: Bump to 4.6.* after https://github.com/robolectric/robolectric/issues/6593 is fixed




        }
    }
    object RobolEctric{
        const val robolectric = "org.robolectric:robolectric:4.5.1"
    }
    object Hilt{
        /**
         * HILT
         */
        const val android = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
        const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltJetpackVersion}"
        const val jetpackCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltJetpackVersion}"

        object Test{
            // For instrumentation tests
            const val AndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
            const val AndroidTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

            // For local unit tests
            const val AndroidUnitTest = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
            const val AndroidUnitTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
        }

    }
    object JUnit{
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }
    object Kotlin{
        private const val kotlin_version = "1.5.20"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        object Coroutine{
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1"

        }

    }
    object Mockito{
        const val mockito="org.mockito:mockito-core:1.10.19"
    }

    object Retrofit{
        private const val retrofit_version = "2.9.0"
        private const val okhttp_version = "4.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val gsonFactory = "com.squareup.retrofit2:converter-gson:$retrofit_version"
        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
        const val okhttpMock = "com.squareup.okhttp3:mockwebserver:$okhttp_version"

    }
    const val gson ="com.google.code.gson:gson:2.3"
}


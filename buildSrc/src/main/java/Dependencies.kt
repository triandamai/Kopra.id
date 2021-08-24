object ApplicationId{
    val id = "com.trian.app"
}

object Modules{
    val data = ":data"
    val domain = ":domain"
    val common = ":common"
    val presentation = ":presentation"

    //features
    val microlife = ":features:microlife"
}

object Releases{
    val versionCode =1
    val versionName="1.0"
}

/**
 * Arranged alphabetically
 */
object Versions {
    val kotlinVersion = "1.5.21"
    val compose_version = "1.0.1"
    val safeArgs = "2.2.2"
    val compileSdkVersion = 30
    val buildTool = "28"
    val minSdk = 22
    val targetSdk = 30
    val hiltGradlePluginVersion = "2.38.1"
    val hiltJetpackVersion = "1.0.0-alpha02"
    val hiltVersion = "2.38.1"
    val roomVersion = "2.2.5"
    val lifecycle = "2.2.0"
    val dagger = "2.24"
}

object DependenciesInjection{
    // Dagger core

    /**
     * HILT
     */
    val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    val hiltJetpack = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltJetpackVersion}"
    val hiltJetpackCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltJetpackVersion}"


    // For instrumentation tests
    val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    val hiltAndroidTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    // For local unit tests
    val hiltAndroidUnitTest = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    val hiltAndroidUnitTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

   }
object Compose{
    val compose_ui = "androidx.compose.ui:ui:${Versions.compose_version}"
    val compose_ui_material ="androidx.compose.material:material:${Versions.compose_version}"
    val compose_ui_preview ="androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
    val compose_ui_activity =  "androidx.activity:activity-compose:1.3.1"
    val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
    val composeLiveDataRuntime = "androidx.compose.runtime:runtime-livedata:${Versions.compose_version}"
    val composeNavigation = "androidx.navigation:navigation-compose:2.4.0-alpha07"

}

object Network{
    private const val ktor_version = "1.5.0"
    val ktorAndroid = "io.ktor:ktor-client-android:$ktor_version"
    val ktorSerialization = "io.ktor:ktor-client-serialization:$ktor_version"
    val ktorGson= "io.ktor:ktor-client-gson:$ktor_version"

    val ktorLogger = "io.ktor:ktor-client-logging-jvm:1.5.0"
}
object KotlinLibraries {
    private const val kotlin_version = "1.5.1"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    val coreKotlinCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_version"
    val androidKotlinCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_version"
}
object LifeCycle{

    // To integrate with ViewModel
   private const val  lifecycle_runtime_ktx = "2.3.1"
    val lifeCycleRuntimeKtx= "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_runtime_ktx"
}
object AndroidLibraries {
    private const val ktx_core = "1.6.0"
    private const val appcompat = "1.3.1"
    private const val material = "1.1.0"

    val dagger2 = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"


    val ktxCore= "androidx.core:core-ktx:$ktx_core"
    val androidMaterial ="com.google.android.material:material:$material"
    val appCompat =  "androidx.appcompat:appcompat:$appcompat"

    // ROOM
    val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // optional - Kotlin Extensions and Coroutines support for Room
    val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompile = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

}

object TestLibraries {
    private const val espresso_core = "3.4.0"
    private const val android_testRunner = "1.1.0"
    private const val junitTest = "1.0.0"

    // ANDROID TEST
    val androidTestRunner = "androidx.test:runner:$android_testRunner"
    val espressoCore = "androidx.test.espresso:espresso-core:$espresso_core"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:$espresso_core"
    val junit = "androidx.test.ext:junit:$junitTest"

    val compose_test_junit= "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
    val compose_test_ui_tooling ="androidx.compose.ui:ui-tooling:${Versions.compose_version}"

    //

}

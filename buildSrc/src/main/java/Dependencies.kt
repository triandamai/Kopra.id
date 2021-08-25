object ApplicationId{
    const val id = "com.trian.app"
}

object Modules{
    const val data = ":data"
    const val domain = ":domain"
    const val common = ":common"
    const val presentation = ":presentation"
    const val app = ":app"

    //features
    const val microlife = ":features:microlife"
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
    const val compileSdkVersion = 30
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

object DependenciesInjection{
    // Dagger core

    /**
     * HILT
     */
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    const val hiltJetpack = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltJetpackVersion}"
    const val hiltJetpackCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltJetpackVersion}"

    // For instrumentation tests
    const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    const val hiltAndroidTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    // For local unit tests
    const val hiltAndroidUnitTest = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    const val hiltAndroidUnitTestCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

   }
object Compose{
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose_version}"
    const val compose_ui_material ="androidx.compose.material:material:${Versions.compose_version}"
    const val compose_ui_preview ="androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
    const val compose_ui_activity =  "androidx.activity:activity-compose:1.3.1"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
    const val composeLiveDataRuntime = "androidx.compose.runtime:runtime-livedata:${Versions.compose_version}"
    const val composeNavigation = "androidx.navigation:navigation-compose:2.4.0-alpha07"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose_version}"
    const val composeIcons = "androidx.compose.material:material-icons-core:${Versions.compose_version}"
    const val composeIconsExtended= "androidx.compose.material:material-icons-extended:${Versions.compose_version}"
    const val composeAccompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:0.14.0"
    const val composeAccompanistPager = "com.google.accompanist:accompanist-pager:0.15.0"
}

object Network{
    private const val ktor_version = "1.5.0"
    const val ktorAndroid = "io.ktor:ktor-client-android:$ktor_version"
    const val ktorSerialization = "io.ktor:ktor-client-serialization:$ktor_version"
    const val ktorGson= "io.ktor:ktor-client-gson:$ktor_version"

    const val ktorLogger = "io.ktor:ktor-client-logging-jvm:1.5.0"
}
object KotlinLibraries {
    private const val kotlin_version = "1.5.1"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    const val coreKotlinCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_version"
    const val androidKotlinCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlin_version"
}
object LifeCycle{

    // To integrate with ViewModel
   private const val  lifecycle_runtime_ktx = "2.3.1"
    const val lifeCycleRuntimeKtx= "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_runtime_ktx"
}
object AndroidLibraries {
    private const val ktx_core = "1.6.0"
    private const val appcompat = "1.3.1"
    private const val material = "1.1.0"

    const val dagger2 = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"


    const val ktxCore= "androidx.core:core-ktx:$ktx_core"
    const val androidMaterial ="com.google.android.material:material:$material"
    const val appCompat =  "androidx.appcompat:appcompat:$appcompat"

    // ROOM
    const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // optional - Kotlin Extensions and Coroutines support for Room
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleCompile = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

}

object TestLibraries {
    private const val espresso_core = "3.4.0"
    private const val android_testRunner = "1.1.0"
    private const val junitTest = "1.0.0"

    // ANDROID TEST
    const val androidTestRunner = "androidx.test:runner:$android_testRunner"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espresso_core"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:$espresso_core"
    const val junit = "androidx.test.ext:junit:$junitTest"
    // Required -- JUnit 4 framework
    const val jUnit=  "junit:junit:4.13.1"
    // Optional -- Robolectric environment
    const val Mockito="org.mockito:mockito-core:1.10.19"

    const val compose_test_junit= "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
    const val compose_test_ui_tooling ="androidx.compose.ui:ui-tooling:${Versions.compose_version}"


    const val googleTruth = "com.google.truth:truth:1.1"
//    instrumentation test

    const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:2.28-alpha"
    const val kaptHiltAndroidtest= "com.google.dagger:hilt-android-compiler:2.28-alpha"
    const val coreTesting ="androidx.arch.core:core-testing:2.1.0"
    const val coroutineTest="org.jetbrains.kotlinx:kotlinx-coroutines-test:1.2.1"

    const val roboElectricTes = "org.robolectric:robolectric:4.4"


}

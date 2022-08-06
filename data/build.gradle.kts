/**
 * Copyright Trian Damai 2022 triandamai@gmail.com
 *
 * */
plugins {
    id("com.android.library")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")

}


android{
    compileSdk = 31
    defaultConfig {
        minSdk = 23
        targetSdk = 30

    }

    compileOptions {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled =true

        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {

    //Loads packaged libraries in the libs folder
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation("androidx.browser:browser:1.2.0")


    with(Libs.AndroidX.Room){
        api(roomRuntime)
        implementation(roomPaging)
        implementation(roomKtx)
        kapt(roomCompiler)
        testImplementation(roomTesting)

    }

    with(Libs.Com.Squareup.Retrofit2){
        implementation(retrofit)
        implementation(gsonFactory)
    }

    with(Libs.Com.Squareup.Okhttp3){
        implementation(okhttp)
        implementation(loggingInterceptor)
        implementation(mockWebServer)
    }

    implementation(Libs.JodaTime.jodaTime)

    implementation(Libs.Com.Squareup.Logcat.logcat)

    //firebase
    with(Libs.Com.Google.Firebase) {
        implementation(platform(bom))
        implementation(auth)
        implementation(firestore)
        implementation(storage)
        implementation(messaging)
        implementation(crashlytics)
        implementation(analytics)
        implementation(functions)

    }


    //allow use await() in firebase task
    with(Libs.Org.Jetbrains.Kotlinx) {
        implementation(googlePlayKotlinCoroutine)
    }


}
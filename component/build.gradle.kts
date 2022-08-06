/**
 * Copyright Trian Damai 2022 triandamai@gmail.com
 *
 * */

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android{

    compileSdk = 31
    defaultConfig {
        minSdk = 23
        targetSdk = 30
    }
    buildFeatures {
        compose= true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.composeCompiler
    }

    compileOptions {
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
}
dependencies {

    implementation(Libs.Com.Github.GrenderG.toasty)
    implementation(project(":data"))

    implementation(Libs.Com.Google.Maps)
    implementation(Libs.Com.Google.MapsUtilsKtx)
    implementation(Libs.Com.Google.MapsKtx)
    implementation(Libs.AndroidX.Fragment)

    implementation(Libs.Com.Google.locationServices)

    implementation(Libs.JodaTime.jodaTime)

    with(Libs.AndroidX.Compose){
        implementation(Libs.AndroidX.Compose.Ui.ui)
        //https://stackoverflow.com/questions/68224361/jetpack-compose-cant-preview-after-updating-to-1-0-0-rc01
        implementation(Libs.AndroidX.Compose.Ui.uiTooling)
        implementation(Libs.AndroidX.Compose.Ui.uiToolingPreview)
        implementation(Libs.AndroidX.Compose.Material.material)
        implementation(Libs.AndroidX.Compose.Runtime.runtimeLivedata)
        implementation(Libs.AndroidX.Compose.Material.icons)
        implementation(Libs.AndroidX.Compose.Material.iconsExtended)
        androidTestImplementation(Libs.AndroidX.Compose.Ui.uiTestJunit4)
        debugImplementation(Libs.AndroidX.Compose.Ui.uiTestManifest)
        debugImplementation(Libs.AndroidX.Compose.Ui.uiTooling)

    }
    with(Libs.Com.Google.Accompanist){
        implementation(accompanistSystemUiController)
        implementation(accompanistNavigationAnimation)
        implementation(accompanistNavigationMaterial)
        implementation(accompanistInset)
        implementation(accompanistSwipeRefresh)
        implementation(accompanistShimmer)
        implementation(accompanistPagerIndicator)

    }

    with(Libs.AndroidX.Lifecycle){
        implementation(lifecycleRuntimeKtx)
        // ViewModel
        implementation(lifecycleViewmodel)
        implementation(lifecycleLivedata)
        implementation(lifecycleRuntime)
        implementation(lifecycleViewmodelSavedstate)
    }

    implementation(Libs.Com.Github.PhilJay.mpAndroidChart)
    implementation(Libs.Br.Com.Devsrsouza.Compose.Icons.Android.octicons)
    implementation(Libs.Com.Github.Skydoves.landscapist)
    implementation(Libs.AndroidX.Appcompat.appcompat)

}
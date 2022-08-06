import org.gradle.kotlin.dsl.provideDelegate
object Libs {

    object Com {
        object Google {

             val Maps by lazy{ "com.google.maps.android:android-maps-utils:2.3.0"}
             val MapsUtilsKtx by lazy{"com.google.maps.android:maps-utils-ktx:3.4.0"}
            val MapsKtx by lazy{"com.google.maps.android:maps-ktx:3.4.0"}
            val locationServices by lazy{"com.google.android.gms:play-services-location:20.0.0"}
            //for firebase sdk
            object Firebase {
                val bom by lazy { "com.google.firebase:firebase-bom:30.2.0" }
                val auth by lazy { "com.google.firebase:firebase-auth-ktx" }
                val firestore by lazy { "com.google.firebase:firebase-firestore-ktx" }
                val storage by lazy { "com.google.firebase:firebase-storage-ktx" }
                val messaging by lazy { "com.google.firebase:firebase-messaging-ktx" }
                val crashlytics by lazy { "com.google.firebase:firebase-crashlytics-ktx" }
                val analytics by lazy { "com.google.firebase:firebase-analytics-ktx" }
                val functions by lazy {"com.google.firebase:firebase-functions-ktx"}
            }

            //for google authentication
            object Android {
                object Gms {
                    val auth by lazy { "com.google.android.gms:play-services-auth:20.0.1" }
                }

                object Flexbox {
                    val flexbox by lazy { "com.google.android.flexbox:flexbox:3.0.0" }

                }

                object Material {
                    val material by lazy { "com.google.android.material:material:1.4.0" }
                }
            }


            //accompanist(external library for jetpack compose)
            object Accompanist {
                const val accompanistVersion = "0.23.1"
                val accompanistSystemUiController by lazy { "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion" }
                val accompanistPager by lazy { "com.google.accompanist:accompanist-pager:0.18.0" }
                val accompanistNavigationAnimation by lazy { "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion" }
                val accompanistNavigationMaterial by lazy { "com.google.accompanist:accompanist-navigation-material:$accompanistVersion" }
                val accompanistInset by lazy { "com.google.accompanist:accompanist-insets:$accompanistVersion" }
                val accompanistSwipeRefresh by lazy { "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion" }
                val accompanistShimmer by lazy { "com.google.accompanist:accompanist-placeholder-material:$accompanistVersion" }
                val accompanistPagerIndicator by lazy { "com.google.accompanist:accompanist-pager-indicators:0.18.0" }
            }

            //dagger hilt
            object Dagger {
                private const val dagger_hilt_version = "2.38.1"
                val hiltAndroid by lazy { "com.google.dagger:hilt-android:$dagger_hilt_version" }
                val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:$dagger_hilt_version" }
                val hiltAndroidTesting by lazy { "com.google.dagger:hilt-android-testing:$dagger_hilt_version" }

            }

            object Truth {
                val truth by lazy { "com.google.truth:truth:1.1" }
            }

            object Code {
                object Gson {
                    val gson by lazy { "com.google.code.gson:gson:2.3" }
                }
            }
        }

        object Squareup {
            //for logging in debugging mode
            object Logcat {
                val logcat by lazy { "com.squareup.logcat:logcat:0.1" }

            }

            object Retrofit2 {
                private var retrofit_version = "2.9.0"
                var retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
                var gsonFactory = "com.squareup.retrofit2:converter-gson:$retrofit_version"
            }

            object Okhttp3 {
                private var okhttp_version = "4.9.0"
                var okhttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
                var loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
                var mockWebServer = "com.squareup.okhttp3:mockwebserver:$okhttp_version"
            }

        }

        object Github {
            object Bumptech {
                object Glide {
                    val glide by lazy { "com.github.bumptech.glide:glide:4.12.0" }
                    val compiler by lazy { "com.github.bumptech.glide:compiler:4.12.0" }
                }
            }

            object Jeziellago {
                val composeMarkdown by lazy { "com.github.jeziellago:compose-markdown:0.2.6" }
            }

            object Skydoves {
                val landscapist by lazy { "com.github.skydoves:landscapist-coil:1.4.1" }
            }

            object Jiangdongguo {
                val androidUsbcamera by lazy { "com.github.jiangdongguo:AndroidUSBCamera:2.3.6" }
            }

            object GrenderG {
                val toasty by lazy { "com.github.GrenderG:Toasty:1.5.2" }
            }

            object PhilJay {
                val mpAndroidChart by lazy { "com.github.PhilJay:MPAndroidChart:v3.1.0" }
            }
        }
    }

    object AndroidX {
        val Fragment by lazy{ "androidx.fragment:fragment:1.3.6"}
        object Paging {
            private const val version = "1.0.0-alpha14"
            val pagingCompose by lazy { "androidx.paging:paging-compose:$version" }
        }

        object Appcompat {
            private const val appcompat_version = "1.4.1"

            val appcompat by lazy { "androidx.appcompat:appcompat:$appcompat_version" }

        }

        object Work {
            const val work_version = "2.7.1"
            val workRuntime by lazy { "androidx.work:work-runtime:$work_version" }
            val workRuntimeKtx by lazy { "androidx.work:work-runtime-ktx:$work_version" }


        }

        object Multidex {
            val multidex by lazy { "androidx.multidex:multidex:2.0.1" }
        }

        object Core {
            val coreKtx by lazy { "androidx.core:core-ktx:1.7.0" }
        }

        object Compose {
            const val compose_version = "1.2.0-alpha02"

            object Ui {

                val ui by lazy { "androidx.compose.ui:ui:$compose_version" }
                val uiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:$compose_version" }
                val uiTooling by lazy { "androidx.compose.ui:ui-tooling:$compose_version" }
                val uiTestJunit4 by lazy { "androidx.compose.ui:ui-test-junit4:$compose_version" }
                val uiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest:$compose_version" }
                var uiTest = "androidx.compose.ui:ui-test:$compose_version"

            }

            object Runtime {
                val runtime by lazy { "androidx.compose.runtime:runtime:$compose_version" }
                val runtimeLivedata by lazy { "androidx.compose.runtime:runtime-livedata:$compose_version" }
            }

            object Material {
                val material by lazy { "androidx.compose.material:material:$compose_version" }
                val icons by lazy { "androidx.compose.material:material-icons-core:$compose_version" }
                val iconsExtended by lazy { "androidx.compose.material:material-icons-extended:$compose_version" }
            }


        }

        object Lifecycle {
            private const val lifecycle_version = "2.5.0-alpha01"
            val lifecycleViewmodelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07" }
            val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0" }
            val lifecycleViewmodel by lazy { "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version" }
            val lifecycleViewmodelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version" }
            val lifecycleLivedata by lazy { "androidx.lifecycle:lifecycle-livedata:$lifecycle_version" }
            val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime:$lifecycle_version" }
            val lifecycleViewmodelSavedstate by lazy { "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version" }
            val lifecycleCompiler by lazy { "androidx.lifecycle:lifecycle-compiler:$lifecycle_version" }
        }

        object Activity {
            val activityCompose by lazy { "androidx.activity:activity-compose:1.4.0" }
        }

        object Arch {
            const val arch_version = "2.1.0"

            object Core {
                val coreTesting by lazy { "androidx.arch.core:core-testing:$arch_version" }
            }
        }

        object Navigation {
            val navigationCompose by lazy { "androidx.navigation:navigation-compose:2.5.0-alpha01" }
        }

        object Hilt {
            val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:1.0.0" }

            //https://stackoverflow.com/questions/67020214/hiltworker-annotation-can-not-be-found
            val hiltWork by lazy { "androidx.hilt:hilt-work:1.0.0" }
            val hiltCompiler by lazy { "androidx.hilt:hilt-compiler:1.0.0" }
            val hiltLifecycleViewmodel by lazy { "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03" }
        }

        object Room {
            private const val room_version = "2.4.1"
            val roomRuntime by lazy { "androidx.room:room-runtime:$room_version" }
            val roomCompiler by lazy { "androidx.room:room-compiler:$room_version" }
            val roomKtx by lazy { "androidx.room:room-ktx:$room_version" }
            val roomTesting by lazy { "androidx.room:room-testing:$room_version" }
            val roomPaging by lazy { "androidx.room:room-paging:$room_version" }

        }

        object Test {
            object Ext {
                val junit by lazy { "androidx.test.ext:junit:1.1.3" }
            }

            object Espresso {
                val espressoCore by lazy { "androidx.test.espresso:espresso-core:3.4.0" }
            }
        }
    }

    object Org {
        object Jetbrains {


            //for use `await()` with google and firebase Task API
            object Kotlinx {

                val googlePlayKotlinCoroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.kotlinCoroutine}" }
                val kotlinxCoroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.kotlinCoroutine}" }
            }

            object Kotlin {
                const val Version = "1.5.31"
                val kotlinStdLib by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$Version" }
                val kotlinReflect by lazy { "org.jetbrains.kotlin:kotlin-reflect:$Version" }
            }
        }

        object Jitsi {
            val jitsiSdk by lazy { "org.jitsi.react:jitsi-meet-sdk:4.1.0" }
        }

        object Mockito {
            val mockitoCore by lazy { "org.mockito:mockito-core:4.0.0" }
        }

        object Robolectric {
            val robolectric by lazy { "org.robolectric:robolectric:4.5.1" }
        }
    }

    object Br {
        object Com {
            object Devsrsouza {
                object Compose {
                    object Icons {
                        object Android {
                            val octicons by lazy { "br.com.devsrsouza.compose.icons.android:octicons:1.0.0" }
                            val feather by lazy { "br.com.devsrsouza.compose.icons.android:feather:1.0.0" }
                        }
                    }
                }
            }
        }
    }

    object JodaTime {
        val jodaTime by lazy { "joda-time:joda-time:2.10.13" }
    }

    object Io {
        object CoilKt {
            val coilCompose by lazy { "io.coil-kt:coil-compose:1.4.0" }
        }

        object Kotest {
            const val Version = "5.1.0"

            val kotestRunnerJunit5 by lazy { "io.kotest:kotest-runner-junit5-jvm:$Version" }

            val kotestAssertionsCoreJvm by lazy { "io.kotest:kotest-assertions-core-jvm:$Version" }
        }
    }

    object Junit {
        val junit by lazy { "junit:junit:4.13.2" }
    }

    object De {
        object Hdodenhof {
            val circleImageView by lazy { "de.hdodenhof:circleimageview:3.1.0" }
        }
    }

    object Nordicsemi {
        var utils = "org.xutils:xutils:3.5.1"
        var scanner = "no.nordicsemi.android.support.v18:scanner:1.4.1"
        var ble = "no.nordicsemi.android:ble:2.2.4"
    }

    object Microlife {
        var ideabus = "com.ideabus.ideabus_structure:ideabuslibrary:2.0.2"
        var scale_sdk = ":localrepo:microlife_scale"
        var sdk = ":localrepo:microlife_release"
    }

}

package com.trian.module

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */
@HiltAndroidApp
class MainApplication : Application(),Configuration.Provider{

    @Inject lateinit var workerFactory: HiltWorkerFactory
    override fun onCreate() {
        super.onCreate()
        YCBTClient.initClient(this,false)
    }

    override fun getWorkManagerConfiguration(): Configuration=
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}


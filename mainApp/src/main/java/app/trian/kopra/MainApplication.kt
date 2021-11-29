package app.trian.kopra

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
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

    }

    override fun getWorkManagerConfiguration(): Configuration=
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}


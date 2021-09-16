package com.trian.smartwatch.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.trian.smartwatch.R
import com.trian.smartwatch.SmartWatchActivity
import com.yucheng.ycbtsdk.YCBTClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmartwatchService :Service(){
    val CHANNED_ID ="cexup.notify"

    companion object{
        var isServiceRunning:Boolean = false
    }

    init {
        isServiceRunning =false
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        YCBTClient.initClient(applicationContext,false)
        isServiceRunning = true

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        notifyUserWhenAppInBackground()

        return START_STICKY
    }

    private fun notifyUserWhenAppInBackground(){
        val notificationIntent = Intent(this, SmartWatchActivity::class.java)
        val pendingIntent =PendingIntent.getActivity(this,0,notificationIntent,0)

        val notifyBuilder = NotificationCompat.Builder(
            this,
            CHANNED_ID
        )
            .setContentTitle("")
            .setContentText("")
            .setSmallIcon(com.trian.component.R.drawable.logo_cexup)
            .setContentIntent(pendingIntent)
            .setColor(resources.getColor(R.color.bg_blue))
            .build()

        startForeground(1,notifyBuilder)
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val appName = getString(R.string.app_name)

           val channel= NotificationChannel(
               CHANNED_ID,
               appName,
               NotificationManager.IMPORTANCE_DEFAULT
           )

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(
                channel
            )
        }
    }

    override fun onDestroy() {

        isServiceRunning = false

        stopForeground(true)

        val broadCastIntent = Intent(this,SmartwatchReceiver::class.java)
        sendBroadcast(broadCastIntent)
        super.onDestroy()

    }
    override fun onBind(p0: Intent?): IBinder? =null
}
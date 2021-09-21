package com.trian.smartwatch.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class SmartwatchReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
    Log.e("RECEIVER","onReceive")

        val workManager =WorkManager.getInstance(context!!);

       val onTimeWorker = OneTimeWorkRequest.Builder(SmartwatchWorker::class.java)
           .build()
        workManager.enqueue(onTimeWorker)

    }
}

class ScreenLockReceiver : BroadcastReceiver() {
    private val TAG = "ScreenLockReceiver"
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        val action = intent.action
        if (action == Intent.ACTION_SCREEN_ON) {
            Log.d(TAG, "onReceive called: screen on")
        } else if (action == Intent.ACTION_SCREEN_OFF) {
            Log.d(TAG, "onReceive called: screen off")
        } else if (action == Intent.ACTION_USER_PRESENT) {
            Log.d(TAG, "onReceive called: screen unlocked")
           // Util().setRandomWallpaper(context)
        }
    }
}
package com.trian.smartwatch.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters


class SmartwatchWorker @WorkerInject constructor(
    @Assisted private val appContext:Context,
    @Assisted workerParameters: WorkerParameters, ) :Worker(
appContext,
    workerParameters
){
    val TAG_WORKER = "WORKER_SMARTWATCH"
    override fun doWork(): Result {
        Log.e(TAG_WORKER,"doWork")
        if(!SmartwatchService.isServiceRunning){
            Log.e(TAG_WORKER,"starting from doWork")
            Intent(appContext, SmartwatchService::class.java)
                .also {
                    ContextCompat.startForegroundService(
                        appContext,it
                    )
                }
        }
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        Log.e(TAG_WORKER,"onStopped")
    }
}
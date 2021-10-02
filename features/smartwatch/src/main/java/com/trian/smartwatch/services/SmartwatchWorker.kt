package com.trian.smartwatch.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.local.room.MeasurementDao
import com.trian.data.repository.IMeasurementRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext


@HiltWorker
class SmartwatchWorker @AssistedInject constructor(
    @Assisted private val appContext:Context,
    @Assisted workerParameters: WorkerParameters,
    private val measurementDao: MeasurementDao,
    private val persistence: Persistence,
    private val measurementRepository:IMeasurementRepository,
    private val dispatcherProvider: DispatcherProvider
    ) :CoroutineWorker(
    appContext,
    workerParameters
){
    val TAG_WORKER = "WORKER_SMARTWATCH"
    override suspend fun doWork(): Result = withContext(dispatcherProvider.io()) {

        val user = persistence.getUser()

        user?.let {

            val data = measurementDao.getNotUploaded(it.user_id)
            measurementRepository.sendMeasurement(data)

        }


        if(!SmartwatchService.isServiceRunning){
            Intent(appContext, SmartwatchService::class.java)
                .also {
                    ContextCompat.startForegroundService(
                        appContext,it
                    )
                }
        }
        return@withContext Result.success()
    }

}
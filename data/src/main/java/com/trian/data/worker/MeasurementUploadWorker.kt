package com.trian.data.worker

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.repository.MeasurementRepository
import com.trian.data.services.SmartwatchService
import com.trian.domain.models.request.toMeasurement
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 04/10/2021
 */


@HiltWorker
class MeasurementUploadWorker @AssistedInject constructor(
    @Assisted private val appContext:Context,
    @Assisted workerParameters: WorkerParameters,
    private val persistence: Persistence,
    private val measurementRepository:MeasurementRepository,
    private val dispatcherProvider: DispatcherProvider
    ) :CoroutineWorker(
    appContext,
    workerParameters
){

    /**
     * this worker for sync between data local into cloud 
     * **/
    override suspend fun doWork(): Result = withContext(dispatcherProvider.io()) {
        val user = persistence.getUser()
        user?.let {


            //get data with status upload = false(the data not yet sync)
            val data = measurementRepository.getNotUploaded(it.user_code)
            val result = measurementRepository.sendMeasurement(data)

            Log.e("WORKER-DATA",data.toString())

            result.data?.let { response ->
                Log.e("WORKER",response.toString())
                val transform = response.data.map {
                    request->
                    request.toMeasurement()
                }
                measurementRepository.saveLocalMeasurement(transform,true)
            }
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
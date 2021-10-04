package com.trian.smartwatch.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.local.room.MeasurementDao
import com.trian.data.repository.IMeasurementRepository
import com.trian.domain.models.request.toMeasurement
import com.trian.domain.models.request.toRequest
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

    /**
     * this worker for sync between data local into cloud 
     * **/
    override suspend fun doWork(): Result = withContext(dispatcherProvider.io()) {

        Log.e("SYNC1","")
        val user = persistence.getUser()
        user?.let {

            //get data with status upload = false(the data not yet sync)
            val data = measurementDao.getNotUploaded(it.user_id)
            Log.e("SYNC3",data.size.toString())
            val result = measurementRepository.sendMeasurement(data)


            result.data?.let { response ->
                val transform = response.data.map {
                    request->
                    Log.e("REQ",request.device.toString())
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
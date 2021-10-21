package com.trian.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
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

        return@withContext Result.success()
    }

}
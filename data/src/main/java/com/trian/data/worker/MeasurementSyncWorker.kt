package com.trian.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.repository.MeasurementRepository
import com.trian.domain.models.request.toMeasurement
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 04/10/2021
 */
@HiltWorker
class MeasurementSyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val dispatcherProvider:DispatcherProvider,
    private val measurementRepository: MeasurementRepository,
    private val persistence: Persistence
):CoroutineWorker(
    appContext,
    workerParameters
) {
    override suspend fun doWork(): Result = withContext(dispatcherProvider.io()){
        val user = persistence.getUser()
        user?.let { it1 ->

            val result = measurementRepository.fetchApiMeasurement(it1.user_id)
            result.data?.let { it ->
                val result = it.data.map {
                    it.toMeasurement()
                }
                measurementRepository.saveLocalMeasurement(result,true)
            }
        }

        return@withContext Result.success()
    }
}
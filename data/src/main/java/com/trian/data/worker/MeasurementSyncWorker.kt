//package com.trian.data.worker
//
//import android.content.Context
//import androidx.hilt.work.HiltWorker
//import androidx.work.CoroutineWorker
//import androidx.work.WorkerParameters
//import com.trian.data.coroutines.DispatcherProvider
//import com.trian.data.local.Persistence
//import dagger.assisted.Assisted
//import dagger.assisted.AssistedInject
//import kotlinx.coroutines.withContext
//
///**
// * Persistence Class
// * Author PT Cexup Telemedicine
// * Created by Trian Damai
// * 04/10/2021
// */
//@HiltWorker
//class MeasurementSyncWorker @AssistedInject constructor(
//    @Assisted private val appContext: Context,
//    @Assisted workerParameters: WorkerParameters,
//    private val dispatcherProvider:DispatcherProvider,
//    private val persistence: Persistence
//):CoroutineWorker(
//    appContext,
//    workerParameters
//) {
//    override suspend fun doWork(): Result = withContext(dispatcherProvider.io()){
//        val user = persistence.getUser()
//
//
//        return@withContext Result.success()
//    }
//}
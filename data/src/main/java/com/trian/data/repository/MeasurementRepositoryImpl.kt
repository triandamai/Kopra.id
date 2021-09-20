package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.room.CexupDatabase
import com.trian.data.local.room.MeasurementDao
import com.trian.data.remote.app.IAppRemoteDataSource
import com.trian.data.utils.networkBoundResource
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.repository.BaseResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

class MeasurementRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val measurementDao: MeasurementDao,
    private val appRemoteDataSource: IAppRemoteDataSource,
):IMeasurementRepository {

    override suspend fun saveLocalMeasurement(measurements: List<Measurement>) {
        measurementDao.measureTransaction(measurements,false)
    }

    override fun getHistory(type:Int,member_id: String,from:Long,to:Long): List<Measurement> = measurementDao.getHistoryByDate(type,member_id,from,to)

}
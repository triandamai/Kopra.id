package com.trian.data.repository

import android.util.Log
import com.google.gson.Gson
import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.di.NetworkModule.BASE_URL_DEVICE
import com.trian.data.local.room.CexupDatabase
import com.trian.data.local.room.MeasurementDao
import com.trian.data.remote.app.IAppRemoteDataSource
import com.trian.data.utils.networkBoundResource
import com.trian.data.utils.safeApiCall
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

    @ExperimentalCoroutinesApi
    override suspend fun getAllMeasurement(): Flow<NetworkStatus<List<Measurement>>> {
        return networkBoundResource(
            query = {fetchLocalUsers()},
            fetch = {fetchApiUsers()},
            saveFetchResult = { response ->
              when(response){
                  is NetworkStatus.Success->{
                      measurementDao.measureTransaction(listOf(response.data!!.data),false)
                  }
                  is NetworkStatus.Error -> {}
                  is NetworkStatus.Loading -> {}
              }


            },
            shouldFetch={false},
            clearData = {},

        )
    }

    override  fun fetchLocalUsers()= flow {
        val data = measurementDao.getLastMeasurement(0,"")
       emit(data)
    }

    override suspend fun sendMeasurement(list: List<Measurement>): NetworkStatus<BaseResponse<List<Measurement>>> {
        return safeApiCall { appRemoteDataSource.sendMeasurement("${BASE_URL_DEVICE}measurements",list) }
    }

    override suspend fun fetchApiUsers() = safeApiCall { appRemoteDataSource.getHistoryMeasurement() }

    override suspend fun saveLocalMeasurement(measurements: List<Measurement>) {
        measurementDao.measureTransaction(measurements,false)
    }

    override suspend fun getHistory(type:Int,member_id: String,from:Long,to:Long): List<Measurement> = measurementDao.getHistoryByDate(type,member_id,from,to)

    override suspend fun getLatestMeasurement(type: Int, member_id: String): List<Measurement> = measurementDao.getLastMeasurement(type,member_id)


}
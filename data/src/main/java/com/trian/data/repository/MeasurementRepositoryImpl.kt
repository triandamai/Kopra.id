package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.common.utils.sdk.SDKConstant.BASE_URL_DEVICE
import com.trian.common.utils.sdk.SDKConstant.URL_HISTORY_MEASUREMENT
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.room.MeasurementDao
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.networkBoundResource
import com.trian.data.utils.safeApiCall
import com.trian.domain.entities.Measurement
import com.trian.domain.models.request.RequestGetMeasurement
import com.trian.domain.models.request.toMeasurement
import com.trian.domain.models.request.toRequest
import com.trian.domain.models.request.BaseResponse
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
    private val appRemoteDataSource: AppRemoteDataSource,

):MeasurementRepository {

    @ExperimentalCoroutinesApi
    override suspend fun getAllMeasurement(member_id: String): Flow<NetworkStatus<List<Measurement>>> {
        return networkBoundResource(
            query = {fetchLocalUsers()},
            fetch = {fetchApiMeasurement(member_id)},
            saveFetchResult = { response ->
              when(response){
                  is NetworkStatus.Success->{
                      val result = response.data?.data?.map {
                          it.toMeasurement()
                      }
                      measurementDao.measureTransaction(result!!,false)
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

    override suspend fun sendMeasurement(list: List<Measurement>): NetworkStatus<BaseResponse<List<RequestGetMeasurement>>> {
        val convert = list.map {
            it.toRequest()
        }
        return safeApiCall {

           appRemoteDataSource.sendMeasurement("${BASE_URL_DEVICE}measurements",convert)
        }
    }

    override suspend fun fetchApiMeasurement(member_id: String): NetworkStatus<BaseResponse<List<RequestGetMeasurement>>> =
        safeApiCall { appRemoteDataSource.syncMeasurement("$BASE_URL_DEVICE$URL_HISTORY_MEASUREMENT",member_id = member_id) }

    override suspend fun saveLocalMeasurement(measurements: List<Measurement>,isUpload:Boolean) =measurementDao.measureTransaction(measurements,isUpload)


    override suspend fun getHistory(type:Int,member_id: String,from:Long,to:Long): List<Measurement> = measurementDao.getHistoryByDate(type,member_id,from,to)

    override suspend fun getLatestMeasurement(type: Int, member_id: String): List<Measurement> = measurementDao.getLastMeasurement(type,member_id)
    override suspend fun getNotUploaded(member_id: String, isUpload: Boolean): List<Measurement> = measurementDao.getNotUploaded(member_id=member_id,is_upload=isUpload)
    override suspend fun deleteAll() {
        measurementDao.deleteAll()
    }


}
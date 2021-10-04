package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.utils.networkBoundResource
import com.trian.data.utils.safeApiCall
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.request.RequestGetMeasurement
import com.trian.domain.repository.BaseResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

interface MeasurementRepository {
    suspend fun getAllMeasurement(member_id: String): Flow<NetworkStatus<List<Measurement>>>

    fun fetchLocalUsers(): Flow<List<Measurement>> = flow {

    }

    suspend fun sendMeasurement(list: List<Measurement>):NetworkStatus<BaseResponse<List<RequestGetMeasurement>>>

    suspend fun fetchApiMeasurement(member_id:String): NetworkStatus<BaseResponse<List<RequestGetMeasurement>>>

    suspend fun saveLocalMeasurement(measurements: List<Measurement>,isUpload:Boolean)

    suspend fun getHistory(type:Int,member_id: String,from:Long,to:Long):List<Measurement>

    suspend fun getLatestMeasurement(type: Int,member_id: String):List<Measurement>

    suspend fun getNotUploaded(member_id: String,isUpload: Boolean=false):List<Measurement>
}
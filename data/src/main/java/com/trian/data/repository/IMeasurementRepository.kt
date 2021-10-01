package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.utils.networkBoundResource
import com.trian.data.utils.safeApiCall
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
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

interface IMeasurementRepository {
    suspend fun getAllMeasurement(): Flow<NetworkStatus<List<Measurement>>>

    fun fetchLocalUsers(): Flow<List<Measurement>> = flow {

    }

    suspend fun sendMeasurement(list: List<Measurement>):NetworkStatus<BaseResponse<List<Measurement>>>

    suspend fun fetchApiUsers(): NetworkStatus<BaseResponse<Measurement>>

    suspend fun saveLocalMeasurement(measurements: List<Measurement>)

    suspend fun getHistory(type:Int,member_id: String,from:Long,to:Long):List<Measurement>

    suspend fun getLatestMeasurement(type: Int,member_id: String):List<Measurement>
}
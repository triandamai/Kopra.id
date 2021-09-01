package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
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
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

interface ICexupRepository {
    @ExperimentalCoroutinesApi
    suspend fun getAllUsers(): Flow<NetworkStatus<List<User>>>

     fun fetchLocalUsers(): Flow<List<User>>

     suspend fun loginUser(username:String,password:String):BaseResponse<List<User>>

     suspend fun loginNurse(username:String,password:String):BaseResponse<List<Nurse>>

     suspend fun sendMeasurementCorporate(measurements:List<Measurement>):BaseResponse<Any>
     suspend fun sendMeasurementCorporate(measurement: Measurement):BaseResponse<Any>
     suspend fun sendMeasurementConsumer(measurements: List<Measurement>):BaseResponse<Any>
     suspend fun sendMeasurementConsumer(measurement: Measurement):BaseResponse<Any>

}
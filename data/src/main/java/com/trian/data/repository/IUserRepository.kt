package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.repository.BaseResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 20/09/2021
 */
interface IUserRepository {

    suspend fun loginUser(username:String,password:String): Flow<NetworkStatus<BaseResponse<User>>>

    suspend fun loginNurse(username:String,password:String): BaseResponse<List<Nurse>>

}
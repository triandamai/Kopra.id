package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.models.request.RequestRegister
import com.trian.domain.models.request.ResponseUser
import com.trian.domain.models.request.WebBaseResponse


/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 20/09/2021
 */
interface UserRepository {

    suspend fun loginUser(username:String,password:String): NetworkStatus<WebBaseResponse<ResponseUser>>

    suspend fun loginGoogle(name: String,email: String):NetworkStatus<WebBaseResponse<ResponseUser>>

    suspend fun registerUser(requestRegister: RequestRegister):NetworkStatus<WebBaseResponse<Any>>
}
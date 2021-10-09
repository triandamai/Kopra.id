package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.entities.User
import com.trian.domain.models.request.RequestRegister
import com.trian.domain.models.request.RequestWithSlug
import com.trian.domain.models.request.ResponseUser
import com.trian.domain.models.request.WebBaseResponse


/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 20/09/2021
 */
interface UserRepository {

    suspend fun loginUser(username:String,password:String): DataStatus<ResponseUser>

    suspend fun loginGoogle(name: String,email: String):DataStatus<ResponseUser>

    suspend fun registerUser(requestRegister: RequestRegister):DataStatus<Any>

    suspend fun forgotPassword(email:String):DataStatus<Any>

    suspend fun signOut()

    suspend fun getCurrentUser():User?
}
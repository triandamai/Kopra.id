package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.room.NurseDao
import com.trian.data.local.room.UserDao
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.domain.entities.User
import com.trian.domain.models.request.RequestRegister
import com.trian.domain.models.request.ResponseUser
import com.trian.domain.models.request.WebBaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 20/09/2021
 */
class UserRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val userDao: UserDao,
    private val nurseDao: NurseDao,
    private val appRemoteDataSource: AppRemoteDataSource,
):UserRepository {

    override suspend fun loginUser(username: String, password: String):NetworkStatus<WebBaseResponse<ResponseUser>> = safeApiCall { appRemoteDataSource.loginUser(username,password) }
    override suspend fun loginGoogle(name: String,email: String): NetworkStatus<WebBaseResponse<ResponseUser>>  =safeApiCall {  appRemoteDataSource.loginGoogle(name, email) }
    override suspend fun registerUser(requestRegister: RequestRegister): NetworkStatus<WebBaseResponse<Any>> = safeApiCall { appRemoteDataSource.registerUser(requestRegister) }


}
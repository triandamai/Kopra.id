package com.trian.data.repository

import android.util.Log
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.local.room.NurseDao
import com.trian.data.local.room.UserDao
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.data.utils.safeExtractWebResponse
import com.trian.domain.entities.User
import com.trian.domain.models.request.*
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
    private val persistence: Persistence,
    private val appRemoteDataSource: AppRemoteDataSource,
):UserRepository {

    override suspend fun loginUser(username: String, password: String):DataStatus<ResponseUser> {
        val data =safeExtractWebResponse(safeApiCall{ appRemoteDataSource.loginUser(username, password)})
        data.data?.let {
            persistence.setUser(it.toUser())
            persistence.setToken(data.token)
        }
        return data
    }
    override suspend fun loginGoogle(name: String,email: String): DataStatus<ResponseUser>  {

        val data =safeExtractWebResponse(safeApiCall { appRemoteDataSource.loginGoogle(name, email) })

        Log.e("loginGoogle",data.data.toString())
        data.data?.let {
            persistence.setUser(it.toUser())
            persistence.setToken(data.token)
        }
        return data
    }

    override suspend fun registerUser(requestRegister: RequestRegister): DataStatus<Any> {
        return safeExtractWebResponse(safeApiCall {appRemoteDataSource.registerUser(requestRegister)})
    }

    override suspend fun forgotPassword(email: String): DataStatus<Any> {
        return safeExtractWebResponse(safeApiCall { appRemoteDataSource.forgotPassword(email) })
    }

    override suspend fun signOut() {
        persistence.signOut()
    }

    override suspend fun getCurrentUser(): User? = persistence.getUser()


}
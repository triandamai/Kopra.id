package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.room.MeasurementDao
import com.trian.data.local.room.NurseDao
import com.trian.data.local.room.UserDao
import com.trian.data.remote.app.IAppRemoteDataSource
import com.trian.data.utils.networkBoundResource
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
 * 20/09/2021
 */
class UserRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val userDao: UserDao,
    private val nurseDao: NurseDao,
    private val appRemoteDataSource: IAppRemoteDataSource
    ):IUserRepository {
    @ExperimentalCoroutinesApi
    override suspend fun getAllUsers(): Flow<NetworkStatus<List<User>>> {
        return networkBoundResource(
            query = { fetchLocalUsers() },
            fetch = {},
            saveFetchResult = { response ->
                //   cexupDatabase.userDao().insert(response.data!!)

            },
            clearData = {}
        )
    }

    override  fun fetchLocalUsers(): Flow<List<User>> = flow {

    }

    override suspend fun loginUser(username: String, password: String): BaseResponse<List<User>> = appRemoteDataSource.loginUser(username,password)

    override suspend fun loginNurse(username: String, password: String): BaseResponse<List<Nurse>> = appRemoteDataSource.loginNurse(username, password)

}
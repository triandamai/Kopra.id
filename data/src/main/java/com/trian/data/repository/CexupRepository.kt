package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.room.CexupDatabase
import com.trian.data.remote.app.IAppRemoteDataSource
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

class CexupRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val cexupDatabase: CexupDatabase,
    private val appRemoteDataSource: IAppRemoteDataSource,
):ICexupRepository {
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
        emit(cexupDatabase.userDao().getAll())
    }

    override suspend fun loginUser(username: String, password: String): BaseResponse<List<User>> = appRemoteDataSource.loginUser(username,password)

    override suspend fun loginNurse(username: String, password: String): BaseResponse<List<Nurse>> = appRemoteDataSource.loginNurse(username, password)

    override suspend fun sendMeasurementCorporate(measurements: List<Measurement>): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMeasurementCorporate(measurement: Measurement): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMeasurementConsumer(measurements: List<Measurement>): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMeasurementConsumer(measurement: Measurement): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

}
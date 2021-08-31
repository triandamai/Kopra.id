package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.utils.networkBoundResource
import com.trian.domain.entities.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICexupRepository {
    @ExperimentalCoroutinesApi
    suspend fun getAllUsers(): Flow<NetworkStatus<List<User>>>

     fun fetchLocalUsers(): Flow<List<User>>
}
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

interface IMeasurementRepository {
   suspend fun saveLocalMeasurement(measurements: List<Measurement>)

   fun getHistory(type:Int,member_id: String,from:Long,to:Long):List<Measurement>
}
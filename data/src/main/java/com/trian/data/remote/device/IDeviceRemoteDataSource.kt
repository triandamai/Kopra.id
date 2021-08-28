package com.trian.data.remote.device

import com.trian.domain.repository.BaseResponse
import com.trian.domain.models.TempData

interface IDeviceRemoteDataSource {
    suspend fun getTempData(): BaseResponse<List<TempData>>
    suspend fun postMeasurement(): BaseResponse<Any>

}
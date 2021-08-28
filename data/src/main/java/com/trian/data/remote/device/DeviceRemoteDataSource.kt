package com.trian.data.remote.device

import com.trian.domain.repository.BaseResponse
import com.trian.domain.models.TempData

class DeviceRemoteDataSource(private val deviceApiService: DeviceApiService):IDeviceRemoteDataSource {
    override suspend fun getTempData(): BaseResponse<List<TempData>> {
        TODO("Not yet implemented")
    }

    override suspend fun postMeasurement(): BaseResponse<Any> {
        TODO("Not yet implemented")
    }
}
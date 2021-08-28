package com.trian.data.remote.device

import com.trian.domain.repository.BaseResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface DeviceApiService {

    @POST
    suspend fun postMeasurement(@Url url: String,body:String): BaseResponse<Any>

    @GET
    suspend fun getTempData(): BaseResponse<String>
}
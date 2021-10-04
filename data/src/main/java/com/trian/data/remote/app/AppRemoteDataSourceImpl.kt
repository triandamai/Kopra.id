package com.trian.data.remote.app

import com.trian.domain.entities.Measurement
import com.trian.domain.repository.BaseResponse
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.*
import com.trian.domain.models.request.RequestGetMeasurement
import com.trian.domain.models.request.RequestPostMeasurement
import retrofit2.Response


/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

class AppRemoteDataSourceImpl(private val apiServices: AppApiServices):AppRemoteDataSource {
    override suspend fun loginUser(
        username: String,
        password: String
    ): Response<BaseResponse<User>> =apiServices.loginUser()

    override suspend fun syncMeasurement(
        url:String,
        member_id:String,
        isAllData:Boolean,
        type:Int,
        getLatest:Boolean,
        fromDate:Long,
        toDate:Long
    ): Response<BaseResponse<List<RequestGetMeasurement>>> =apiServices.syncMeasurement(
       url,
        member_id,
        isAllData,
        type,
        getLatest,
        fromDate,
        toDate

    )


    override suspend fun sendMeasurement(url: String, data: List<RequestPostMeasurement>): Response<BaseResponse<List<RequestGetMeasurement>>> = apiServices.sendMeasurement(url,data)


}
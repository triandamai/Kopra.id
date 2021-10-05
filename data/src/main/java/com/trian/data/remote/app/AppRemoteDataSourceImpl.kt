package com.trian.data.remote.app

import com.trian.domain.entities.User
import com.trian.domain.models.request.*
import okhttp3.ResponseBody
import retrofit2.Response


/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

class AppRemoteDataSourceImpl(
    private val apiServices: AppApiServices
):AppRemoteDataSource {
    override suspend fun loginUser(
        username: String,
        password: String
    ): Response<WebBaseResponse<ResponseUser>> =apiServices.loginUser(
        RequestLogin(
            username=username,
            password = password
        )
    )

    override suspend fun loginGoogle(
        name: String,
        email: String
    ): Response<WebBaseResponse<ResponseUser>> = apiServices.loginGoogle(
       RequestLoginGoogle(
           name=name,
           email = email
       )
    )

    override suspend fun registerUser(requestRegister: RequestRegister): Response<WebBaseResponse<Any>> = apiServices.registerPatient(
        requestRegister
    )

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
package com.trian.data.remote.app

import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.*
import com.trian.domain.models.request.*
import retrofit2.Response
import retrofit2.http.*
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

interface AppApiServices {


    @POST("api/login/patient")
    suspend fun loginUser(
        @Body requestLoginGoogle: RequestLogin
    ): Response<WebBaseResponse<ResponseUser>>


    @POST("api/login/provider")
    suspend fun loginGoogle(
         @Body requestLoginGoogle: RequestLoginGoogle
    ): Response<WebBaseResponse<ResponseUser>>


    @POST("api/register/patient")
    suspend fun registerPatient(
        @Body requestRegister: RequestRegister
    ):Response<WebBaseResponse<Any>>

    @GET
    suspend fun syncMeasurement(
        @Url url:String,
        @Query("member_id") member_id:String,
        @Query("all") isAllData:Boolean,
        @Query("type") type:Int,
        @Query("latest") getLatest:Boolean,
        @Query("from") fromDate:Long,
        @Query("to") toDate:Long
    ):Response<BaseResponse<List<RequestGetMeasurement>>>

    @POST
    suspend fun sendMeasurement(@Url url:String, @Body data:List<RequestPostMeasurement>):Response<BaseResponse<List<RequestGetMeasurement>>>


}
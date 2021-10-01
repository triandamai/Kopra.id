package com.trian.data.remote.app

import com.trian.domain.entities.Measurement
import com.trian.domain.repository.BaseResponse
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.*
import retrofit2.Response
import retrofit2.http.*
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

interface AppApiServices {

    @POST("/login/nurse")
    suspend fun loginNurse(): BaseResponse<List<Nurse>>

    @POST("/login/patient")
    suspend fun loginUser(): Response<BaseResponse<User>>

    @POST("/register/patient")
    suspend fun registerUser(): BaseResponse<List<User>>

    @GET("/article")
    suspend fun getArticle():BaseResponse<List<Article>>

    @GET("/doctor")
    suspend fun getDoctor():BaseResponse<List<Doctor>>

    @POST("/doctor/booking")
    suspend fun sendBooking():BaseResponse<List<Any>>

    @GET("/user/orders")
    suspend fun getListOrder():BaseResponse<List<Order>>

    @GET("/user/order/show")
    suspend fun getDetailOrder():BaseResponse<List<Any>>

    @GET("/doctor/get-time-list")
    suspend fun getListAvailableDoctorTime():BaseResponse<List<AvailableTime>>

    @GET("/doctor/show-speciality")
    suspend fun getListSpeciality():BaseResponse<List<Speciality>>

    @GET("/video-conference/meeting-room")
    suspend fun getMeetingRoom():BaseResponse<List<String>>

    @GET("/record-measurement")
    suspend fun getHealthStatus(
        @Query("token") token:String,
        @Query("account_number") account_number:String
    ):BaseResponse<List<HealthStatus>>

    @GET("/hospital")
    suspend fun getListHospital():BaseResponse<List<Hospital>>

    @GET("/ecommerce/product")
    suspend fun getListProduct():BaseResponse<List<Product>>

    @GET("/patient-records")
    suspend fun getPersonalRecords():BaseResponse<List<PatientRecord>>

    @POST
    suspend fun sendMeasurement(@Url url:String,@Body data:List<Measurement>):Response<BaseResponse<List<Measurement>>>




}
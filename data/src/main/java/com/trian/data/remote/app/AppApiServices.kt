package com.trian.data.remote.app

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

    @POST("api/doctor")
    suspend fun doctorList():Response<WebBaseResponse<List<Doctor>>>

    @POST("api/doctor/show-speciality")
    suspend fun specialist(
        @Body requestSpecialist: RequestWithSlug
    ):Response<WebBaseResponse<List<Doctor>>>

    @POST("api/doctor/show")
    suspend fun detailDoctor(
        @Body requestDetailDoctor:RequestWithSlug
    ):Response<WebBaseResponse<Doctor>>

    @POST("api/password/email")
    suspend fun forgotPassword(
        @Body requestEmail:RequestWithSlug
    ):Response<WebBaseResponse<Any>>
    
    @POST("api/article")
    suspend fun article():Response<WebBaseResponse<List<Article>>>

    @POST("api/hospital")
    suspend fun hospital():Response<WebBaseResponse<List<Hospital>>>

    @POST("api/user/orders")
    suspend fun listOrder(
        @Query("user_id") user_id:String,
        @Query("doctor_has_hospital") doctor_has_hospital:String?=null
    ):Response<WebBaseResponse<List<Order>>>

    @POST("api/hospital/show")
    suspend fun detailHospital(
        @Body requestWithSlug: RequestWithSlug
    ): Response<WebBaseResponse<Hospital>>

    @GET("api/speciality")
    suspend fun speciality():Response<WebBaseResponse<List<Speciality>>>

    @POST("api/user/order/show")
    suspend fun detailOrder(
        @Body transaction_id:String
    ): Response<WebBaseResponse<Order>>

    @POST("api/doctor/get-time-list")
    suspend fun timelistdoctor(
        @Body doctor_has_hospital_id:String,
        date:String,
        appointment:String,
    ):Response<WebBaseResponse<List<TimeListDoctor>>>

    @POST("api/video-conference/meeting-room")
    suspend fun getMeetingRoom(
        @Body meeting_id:String,
        username:String,
        token:String,
    ):Response<WebBaseResponse<MeetingRoom>>

    @POST("api/doctor/booking")
    suspend fun sendBookingDoctor(
        @Body requestBookingDoctor: RequestBookingDoctor
    ): Response<WebBaseResponse<Any>>

    @POST("api/ecommerce/product")
    suspend fun getProduct():Response<WebBaseResponse<List<Product>>>



}
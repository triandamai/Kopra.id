package com.trian.data.remote.app

import com.trian.common.utils.utils.getLastDayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.domain.models.*
import com.trian.domain.models.request.*
import retrofit2.Response

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

interface AppRemoteDataSource {


    suspend fun loginUser(username:String,password:String):Response<WebBaseResponse<ResponseUser>>
    suspend fun loginGoogle(name:String,email:String):Response<WebBaseResponse<ResponseUser>>

    suspend fun registerUser(requestRegister: RequestRegister):Response<WebBaseResponse<Any>>

    suspend fun forgotPassword(email: String):Response<WebBaseResponse<Any>>

    suspend fun syncMeasurement(
        url:String,
        member_id:String,
        isAllData:Boolean=true,
        type:Int=1,
        getLatest:Boolean=true,
        fromDate:Long= getLastDayTimeStamp(),
        toDate:Long= getTodayTimeStamp()
    ): Response<BaseResponse<List<RequestGetMeasurement>>>


    suspend fun sendMeasurement(
        url:String,
        data:List<RequestPostMeasurement>):Response<BaseResponse<List<RequestGetMeasurement>>>

    suspend fun getDoctorList():Response<WebBaseResponse<List<Doctor>>>
    suspend fun getSpecialist(slug:String):Response<WebBaseResponse<List<Doctor>>>
    suspend fun getDetailDoctor(slug: String):Response<WebBaseResponse<Doctor>>
    suspend fun getHospital():Response<WebBaseResponse<List<Hospital>>>
    suspend fun getArticle():Response<WebBaseResponse<List<Article>>>
    suspend fun getListOrder(userId:String):Response<WebBaseResponse<List<Order>>>
    suspend fun getDetailHospital(slug: String) : Response<WebBaseResponse<Hospital>>
    suspend fun getSpeciality():Response<WebBaseResponse<List<Speciality>>>
    suspend fun getDetailOrder(transaction_id:String):Response<WebBaseResponse<Order>>
    suspend fun getTimeListDoctor(doctor_has_hospital_id:String,date:String,appoinment:String):Response<WebBaseResponse<List<TimeListDoctor>>>
    suspend fun getMeetingRoom(meeting_id:String,username: String,token:String):Response<WebBaseResponse<MeetingRoom>>
    suspend fun sendBookingDoctor(requestBookingDoctor: RequestBookingDoctor):Response<WebBaseResponse<Any>>
    suspend fun getProduct():Response<WebBaseResponse<List<Product>>>
}
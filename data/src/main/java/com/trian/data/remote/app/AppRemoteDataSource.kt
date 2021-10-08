package com.trian.data.remote.app

import com.trian.common.utils.utils.getLastDayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.domain.entities.User
import com.trian.domain.models.Doctor
import com.trian.domain.models.Speciality
import com.trian.domain.models.request.*
import okhttp3.ResponseBody
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

    suspend fun doctorList():Response<WebBaseResponse<List<Doctor>>>
    suspend fun specialist(slug:String):Response<WebBaseResponse<List<Speciality>>>
    suspend fun detailDoctor(slug: String):Response<WebBaseResponse<Doctor>>
}
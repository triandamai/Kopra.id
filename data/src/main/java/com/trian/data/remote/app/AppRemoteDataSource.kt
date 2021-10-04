package com.trian.data.remote.app

import com.trian.common.utils.utils.getLastDayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.*
import com.trian.domain.models.request.RequestGetMeasurement
import com.trian.domain.models.request.RequestPostMeasurement
import com.trian.domain.repository.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Query
import retrofit2.http.Url
/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

interface AppRemoteDataSource {


    suspend fun loginUser(username:String,password:String):Response<BaseResponse<User>>
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
}
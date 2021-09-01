package com.trian.data.remote.app

import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.*
import com.trian.domain.repository.BaseResponse
import retrofit2.http.Body
import retrofit2.http.Url
/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

interface IAppRemoteDataSource {

    suspend fun loginNurse(username:String,password:String): BaseResponse<List<Nurse>>


    suspend fun loginUser(username:String,password:String): BaseResponse<List<User>>


    suspend fun registerUser(): BaseResponse<List<User>>


    suspend fun getArticle(): BaseResponse<List<Article>>


    suspend fun getDoctor(): BaseResponse<List<Doctor>>


    suspend fun sendBooking(): BaseResponse<List<Any>>


    suspend fun getListOrder(): BaseResponse<List<Order>>


    suspend fun getDetailOrder(): BaseResponse<List<Any>>


    suspend fun getListAvailableDoctorTime(): BaseResponse<List<AvailableTime>>


    suspend fun getListSpeciality(): BaseResponse<List<Speciality>>


    suspend fun getMeetingRoom(): BaseResponse<List<String>>


    suspend fun getHealthStatus(
        token: String,
        account_number: String
    ): BaseResponse<List<HealthStatus>>


    suspend fun getListHospital(): BaseResponse<List<Hospital>>


    suspend fun getListProduct(): BaseResponse<List<Product>>


    suspend fun getPersonalRecords(): BaseResponse<List<PatientRecord>>

    suspend fun sendMeasurement(
        url:String,
        data:String):BaseResponse<List<Any>>
}
package com.trian.data.remote.app

import com.trian.domain.entities.Measurement
import com.trian.domain.repository.BaseResponse
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.*
import retrofit2.Response


/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

class AppRemoteDataSource(private val apiServices: AppApiServices):IAppRemoteDataSource {
    override suspend fun loginNurse(username:String,password:String): BaseResponse<List<Nurse>> =apiServices.loginNurse()

    override suspend fun loginUser(username: String,password: String): Response<BaseResponse<User>> = apiServices.loginUser()
    override suspend fun registerUser(): BaseResponse<List<User>> = apiServices.registerUser()

    override suspend fun getArticle(): BaseResponse<List<Article>> = apiServices.getArticle()

    override suspend fun getDoctor(): BaseResponse<List<Doctor>> = apiServices.getDoctor()
    override suspend fun getHistoryMeasurement(): Response<BaseResponse<Measurement>> {
        TODO("Not yet implemented")
    }

    override suspend fun sendBooking(): BaseResponse<List<Any>> = apiServices.sendBooking()
    override suspend fun getListOrder(): BaseResponse<List<Order>> = apiServices.getListOrder()

    override suspend fun getDetailOrder(): BaseResponse<List<Any>> = apiServices.getDetailOrder()

    override suspend fun getListAvailableDoctorTime(): BaseResponse<List<AvailableTime>> = apiServices.getListAvailableDoctorTime()

    override suspend fun getListSpeciality(): BaseResponse<List<Speciality>> = apiServices.getListSpeciality()

    override suspend fun getMeetingRoom(): BaseResponse<List<String>> = apiServices.getMeetingRoom()
    override suspend fun getHealthStatus(
        token: String,
        account_number: String
    ): BaseResponse<List<HealthStatus>> =apiServices.getHealthStatus(token,account_number)

    override suspend fun getListHospital(): BaseResponse<List<Hospital>> = apiServices.getListHospital()

    override suspend fun getListProduct(): BaseResponse<List<Product>> = apiServices.getListProduct()

    override suspend fun getPersonalRecords(): BaseResponse<List<PatientRecord>> = apiServices.getPersonalRecords()

    override suspend fun sendMeasurement(url: String, data: String): BaseResponse<List<Any>> = apiServices.sendMeasurement(url,data)


}
package com.trian.data.remote.app

import com.trian.domain.models.*
import com.trian.domain.models.request.*
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

    override suspend fun forgotPassword(email: String): Response<WebBaseResponse<Any>> = apiServices.forgotPassword(
        requestEmail = RequestWithSlug(
            email
        )
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

    override suspend fun getDoctorList(): Response<WebBaseResponse<List<Doctor>>> = apiServices.doctorList()
    override suspend fun getSpecialist(slug:String): Response<WebBaseResponse<List<Speciality>>> = apiServices.specialist(
        RequestWithSlug(
            slug = slug
        )
    )

    override suspend fun getDetailDoctor(slug: String): Response<WebBaseResponse<Doctor>> = apiServices.detailDoctor(
        requestDetailDoctor =  RequestWithSlug(
            slug = slug
        )
    )

    override suspend fun getHospital(): Response<WebBaseResponse<List<Hospital>>> = apiServices.hospital()

    override suspend fun getArticle(): Response<WebBaseResponse<List<Article>>> = apiServices.article()

    override suspend fun getListOrder(
        userId:String
    ): Response<WebBaseResponse<List<Order>>> =apiServices.listOrder(userId)

    override suspend fun getDetailHospital(slug: String): Response<WebBaseResponse<Hospital>> = apiServices.detailHospital(
        RequestWithSlug(slug = slug)
    )

    override suspend fun getListSpeciality(): Response<WebBaseResponse<List<Specialist>>> = apiServices.listSpeciality()

    override suspend fun getDetailOrder(transaction_id:String): Response<WebBaseResponse<Order>> = apiServices.detailOrder(
        transaction_id = transaction_id)

    override suspend fun getTimeListDoctor(
        doctor_has_hospital_id: String,
        date: String,
        appoinment: String
    ): Response<WebBaseResponse<List<TimeListDoctor>>> = apiServices.timelistdoctor(
        doctor_has_hospital_id = doctor_has_hospital_id,
        date = date,
        appointment = appoinment
    )

    override suspend fun getMeetingRoom(
        meeting_id: String,
        username: String,
        token: String
    ): Response<WebBaseResponse<MeetingRoom>> = apiServices.getMeetingRoom(
        meeting_id, username, token
    )

    override suspend fun sendBookingDoctor(requestBookingDoctor: RequestBookingDoctor): Response<WebBaseResponse<Any>> = apiServices.sendBookingDoctor(
        requestBookingDoctor
    )
}
package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.data.utils.safeExtractWebResponse
import com.trian.domain.models.*
import com.trian.domain.models.request.RequestBookingDoctor

class DoctorRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource,
):DoctorRepository {
    override suspend fun doctorList(): DataStatus<List<Doctor>> = safeExtractWebResponse(
        safeApiCall { appRemoteDataSource.getDoctorList() }
    )
    override suspend fun specialist(slug:String): DataStatus<List<Doctor>> = safeExtractWebResponse(
        safeApiCall { appRemoteDataSource.getSpecialist(slug = slug) }
    )
    override suspend fun detailDoctor(slug: String): DataStatus<Doctor> = safeExtractWebResponse(
        safeApiCall { appRemoteDataSource.getDetailDoctor(slug=slug) }
    )
    override suspend fun listOrder(userId:String): DataStatus<List<Order>> = safeExtractWebResponse(
        safeApiCall { appRemoteDataSource.getListOrder(userId) }
    )

    override suspend fun speciality(): DataStatus<List<Speciality>> = safeExtractWebResponse(
        safeApiCall { appRemoteDataSource.getSpeciality() }
    )

    override suspend fun detailOrder(transaction_id:String): DataStatus<Order> = safeExtractWebResponse(
        safeApiCall { appRemoteDataSource.getDetailOrder(transaction_id = transaction_id) }
    )

    override suspend fun timeListDoctor(
        doctor_has_hospital_id: String,
        date: String,
        appoinment: String
    ): DataStatus<List<TimeListDoctor>> = safeExtractWebResponse(
        safeApiCall { appRemoteDataSource.getTimeListDoctor(
            doctor_has_hospital_id, date, appoinment
        ) }
    )

    override suspend fun getMeetingRoom(
        meeting_id: String,
        username: String,
        token: String
    ): DataStatus<MeetingRoom> = safeExtractWebResponse(
        safeApiCall {
            appRemoteDataSource.getMeetingRoom(
                meeting_id, username, token
            )
        }
    )

    override suspend fun sendBookingDoctr(requestBookingDoctor: RequestBookingDoctor): DataStatus<Any> = safeExtractWebResponse(
        safeApiCall { appRemoteDataSource.sendBookingDoctor(
            requestBookingDoctor
        ) }
    )
}
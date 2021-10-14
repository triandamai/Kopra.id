package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.models.*
import com.trian.domain.models.request.WebBaseResponse

interface DoctorRepository {

    suspend fun doctorList(): DataStatus<List<Doctor>>

    suspend fun specialist(slug:String): DataStatus<List<Doctor>>

    suspend fun detailDoctor(slug: String): DataStatus<Doctor>

    suspend fun listOrder(userId:String):DataStatus<List<Order>>

    suspend fun listSpeciality():DataStatus<List<Specialist>>

    suspend fun detailOrder(transaction_id:String):DataStatus<Order>

    suspend fun timeListDoctor(
        doctor_has_hospital_id: String,
        date: String,
        appoinment: String
    ):DataStatus<List<TimeListDoctor>>

}
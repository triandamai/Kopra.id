package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.models.Doctor
import com.trian.domain.models.Order
import com.trian.domain.models.Speciality
import com.trian.domain.models.request.WebBaseResponse

interface DoctorRepository {

    suspend fun doctorList(): DataStatus<List<Doctor>>

    suspend fun specialist(slug:String): DataStatus<List<Speciality>>

    suspend fun detailDoctor(slug: String): DataStatus<Doctor>

    suspend fun listOrder(userId:String):DataStatus<List<Order>>

}
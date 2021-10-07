package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.models.Doctor
import com.trian.domain.models.request.WebBaseResponse

interface DoctorRepository {
    suspend fun doctorList(): NetworkStatus<WebBaseResponse<List<Doctor>>>
}
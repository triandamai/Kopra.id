package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.models.Hospital
import com.trian.domain.models.request.WebBaseResponse

interface HospitalRepository {
    suspend fun hospital():DataStatus<List<Hospital>>
}
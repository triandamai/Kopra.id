package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.domain.models.Doctor
import com.trian.domain.models.Speciality
import com.trian.domain.models.request.WebBaseResponse

class DoctorRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource,
):DoctorRepository {
    override suspend fun doctorList(): NetworkStatus<WebBaseResponse<List<Doctor>>> = safeApiCall { appRemoteDataSource.doctorList() }
    override suspend fun specialist(slug:String): NetworkStatus<WebBaseResponse<List<Speciality>>> = safeApiCall { appRemoteDataSource.specialist(slug = slug) }
}
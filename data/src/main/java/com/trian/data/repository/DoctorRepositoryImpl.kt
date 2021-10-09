package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.data.utils.safeExtractWebResponse
import com.trian.domain.models.Doctor
import com.trian.domain.models.Speciality
import com.trian.domain.models.request.WebBaseResponse

class DoctorRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource,
):DoctorRepository {
    override suspend fun doctorList(): DataStatus<List<Doctor>> = safeExtractWebResponse(safeApiCall { appRemoteDataSource.doctorList() })
    override suspend fun specialist(slug:String): DataStatus<List<Speciality>> = safeExtractWebResponse(safeApiCall { appRemoteDataSource.specialist(slug = slug) })
    override suspend fun detailDoctor(slug: String): DataStatus<Doctor> = safeExtractWebResponse(safeApiCall { appRemoteDataSource.detailDoctor(slug=slug) })
}
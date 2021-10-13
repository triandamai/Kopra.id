package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.data.utils.safeExtractWebResponse
import com.trian.domain.models.Hospital
import com.trian.domain.models.request.RequestWithSlug

class HospitalRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource
):HospitalRepository {
    override suspend fun hospital(): DataStatus<List<Hospital>> = safeExtractWebResponse(safeApiCall { appRemoteDataSource.getHospital() })
    override suspend fun detailHospital(slug: String): DataStatus<Hospital> = safeExtractWebResponse(safeApiCall { appRemoteDataSource.getDetailHospital(slug) })
}
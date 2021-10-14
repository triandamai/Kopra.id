package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.data.utils.safeExtractWebResponse
import com.trian.domain.models.Product

class EcommerceRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource,
):EcommerceRepository {
    override suspend fun getProduct(): DataStatus<List<Product>> = safeExtractWebResponse(
        safeApiCall {
            appRemoteDataSource.getProduct()
        }
    )
}
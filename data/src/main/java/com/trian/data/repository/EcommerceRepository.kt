package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.domain.models.Product
import com.trian.domain.models.request.WebBaseResponse
import retrofit2.Response

interface EcommerceRepository {
    suspend fun getProduct():DataStatus<List<Product>>
}
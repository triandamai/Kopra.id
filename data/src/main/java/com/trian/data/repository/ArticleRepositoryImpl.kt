package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.domain.models.Article
import com.trian.domain.models.request.WebBaseResponse

class ArticleRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource
):ArticleRepository {
    override suspend fun article(): NetworkStatus<WebBaseResponse<List<Article>>> = safeApiCall { appRemoteDataSource.article() }
}
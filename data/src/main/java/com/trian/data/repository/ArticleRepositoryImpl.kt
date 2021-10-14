package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.utils.safeApiCall
import com.trian.data.utils.safeExtractWebResponse
import com.trian.domain.models.Article


class ArticleRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource
):ArticleRepository {
    override suspend fun getListArticle(): DataStatus<List<Article>> = safeExtractWebResponse(safeApiCall { appRemoteDataSource.getArticle() })
}
package com.trian.data.repository

import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.models.Article
import com.trian.domain.models.request.WebBaseResponse

interface ArticleRepository {
    suspend fun article():NetworkStatus<WebBaseResponse<List<Article>>>
}
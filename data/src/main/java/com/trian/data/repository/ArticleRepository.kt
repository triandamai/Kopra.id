package com.trian.data.repository

import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.domain.models.Article
import com.trian.domain.models.request.WebBaseResponse

interface ArticleRepository {
    suspend fun getListArticle():DataStatus<List<Article>>
}
package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Article(val id: Long,
                   val category: String,
                   val categorySlug: String,
                   val slug: String,
                   val title: String,
                   val content: String,
                   val thumbOriginal: String,
                   val thumb: String)

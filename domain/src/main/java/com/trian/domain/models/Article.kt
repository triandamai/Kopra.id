package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Article(val id: Long,
                   val category: String,
                   val category_slug: String,
                   val slug: String,
                   val title: String,
                   val content: String,
                   val thumb_original: String,
                   val thumb: String)

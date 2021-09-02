package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Product(    val id: Long,
                       val category: Long,
                       val slug: String,
                       val title: String,
                       val description: String,
                       val price: String,
                       val stock: Long,
                       val view: Long,
                       val link: String,
                       val thumb: Any? = null,
                       val original: Any? = null)

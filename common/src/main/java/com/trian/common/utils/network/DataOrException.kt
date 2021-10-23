package com.trian.common.utils.network

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */
data class DataOrException<T, E : Exception?>(
    var data: T? = null,
    var e: E? = null
)

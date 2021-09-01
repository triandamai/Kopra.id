package com.trian.common.utils.network

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

sealed class NetworkStatus<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?) : NetworkStatus<T>(data)
    class Error<T>(errorMessage: String?, data: T? = null) : NetworkStatus<T>(data, errorMessage)
    class Loading<T>(data: T? = null) : NetworkStatus<T>(data)
}

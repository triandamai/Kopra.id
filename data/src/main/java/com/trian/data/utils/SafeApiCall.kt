package com.trian.data.utils

import android.util.Log
import com.trian.common.utils.network.*
import com.trian.domain.BaseResponse
import io.ktor.client.features.*
import io.ktor.utils.io.*
import java.nio.channels.UnresolvedAddressException

suspend fun <T : Any> safeApiCall(call: suspend () -> BaseResponse<T>): NetworkStatus<T> {

    try {
        val response = call.invoke()

        Log.e("KTOR","${response.toString()}")
        if(response.success){
           return NetworkStatus.Success(response.data)
        }
        return NetworkStatus.Error(response.message)
    } catch (e: Throwable) {
        return when (e) {
            is ClientRequestException -> {
                when (e.response.status.value) {
                    in 401..403 -> {
                        NetworkStatus.Error(UNAUTHORIZED)
                    }
                    in 500..600 -> {
                        NetworkStatus.Error(INTERNAL_SERVER_ERROR)
                    }
                    else -> {
                        NetworkStatus.Error(UNKNOWN_HOST_EXCEPTION)
                    }
                }
            }
            is UnresolvedAddressException -> {
                NetworkStatus.Error(SOCKET_TIME_OUT_EXCEPTION)
            }
            is CancellationException -> {
                NetworkStatus.Error(SOCKET_TIME_OUT_EXCEPTION)
            }
            else -> {

                NetworkStatus.Error(e.message)
            }
        }
    }

}
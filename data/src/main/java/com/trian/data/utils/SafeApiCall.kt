package com.trian.data.utils


import android.util.Log
import com.trian.common.utils.network.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */


suspend fun <T> safeApiCall(call: suspend () -> Response<T>): NetworkStatus<T> {

    try {
        val response = call.invoke()


        if (response.isSuccessful) {
            if (response.body() != null) {
                return NetworkStatus.Success(response.body())
            }
        }
        return NetworkStatus.Error(response.message())
    } catch (e: Exception) {
        Log.e("safeApiCall 24",e.stackTraceToString())
        return when (e) {
            is IOException ->{
                NetworkStatus.Error(e.cause?.message)
            }
            is ConnectException -> {
                NetworkStatus.Error(CONNECT_EXCEPTION)
            }
            is UnknownHostException -> {
                NetworkStatus.Error(UNKNOWN_HOST_EXCEPTION)
            }
            is SocketTimeoutException -> {
                NetworkStatus.Error(SOCKET_TIME_OUT_EXCEPTION)
            }
            is HttpException -> {
                NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
            else -> {
                NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
        }
    }
}
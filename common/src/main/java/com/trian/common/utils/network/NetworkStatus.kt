package com.trian.common.utils.network

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

sealed class NetworkStatus<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T?) : NetworkStatus<T>(data)
    class Error<T>(errorMessage: String?, data: T? = null) : NetworkStatus<T>(data, errorMessage)
}

sealed class DataStatus<T>(
    val code:Int=200,
    val data:T?=null,
    val message:String?="default",
    val token:String? = ""){
    class HasData<T>(code:Int,data:T,token: String?=""):DataStatus<T>(code,data,token=token)
    class NoData<T>(code:Int,message: String):DataStatus<T>(code=code,message=message)
    class Loading<T>(message: String):DataStatus<T>(message=message)
}

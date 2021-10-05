package com.trian.domain.models.request

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class BaseResponse<T>(
    val code:Int,
    val status:String?,
    val data:T,
    val message:String?
    )

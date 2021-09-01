package com.trian.domain.repository

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class BaseResponse<T>(val success:Boolean,val data:T,val message:String)

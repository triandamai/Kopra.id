package com.trian.domain.models

data class BaseResponse<T>(val success:Boolean,val data:T,val message:String)

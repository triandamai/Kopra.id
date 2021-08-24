package com.trian.domain

data class BaseResponse<T>(val success:Boolean,val data:T,val message:String)

package com.trian.domain.repository

data class BaseResponse<T>(val success:Boolean,val data:T,val message:String)

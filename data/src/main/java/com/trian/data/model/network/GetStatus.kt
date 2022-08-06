package com.trian.data.model.network


sealed class GetStatus<T>(
    val data:T?=null,
    val message:String?="default", ){
    class Idle<T>():GetStatus<T>()
    class HasData<T>(data: T?):GetStatus<T>(data,"")
    class NoData<T>( message: String):GetStatus<T>(message=message)
    class Loading<T>():GetStatus<T>()
}
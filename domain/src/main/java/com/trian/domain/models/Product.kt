package com.trian.domain.models

/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 **/
enum class UnitProduct{
    KG,
    HARI,
    UNKNOWN
}
data class Product(
    val uid:String = "",
    val storeUid:String = "",
    val productName:String = "",
    val category:String = "",
    val price:Double = 0.0,
    val thumbnail:String = "",
    val unit:UnitProduct = UnitProduct.KG,
    val createdAt:Long = 0,
    val updatedAt:Long = 0
)

package com.trian.domain.models
/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
enum class UnitProduct{
    KG,
    HARI
}
data class Product(
    val uid:String,
    val storeUid:String,
    val productName:String,
    val category:String,
    val price:Double,
    val thumbnail:String,
    val unit:UnitProduct,
    val createdAt:Long,
    val updatedAt:Long
)

package com.trian.domain.models

/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
data class Transaction(
    val uid:String,
    val buyer:String,
    val seller:String,
    val totalPrice:Double,
    val detail:List<ProductTransaction>,
    val createdAt:Long,
    val updatedAt:Long
)

data class ProductTransaction(
    val uid:String,
    val productUid:String,
    val quantity:String,
    val price:String,
    val createdAt:Long,
    val updatedAt:Long
)

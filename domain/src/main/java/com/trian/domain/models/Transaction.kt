package com.trian.domain.models

/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
data class Transaction(
    val uid:String = "",
    val buyer:String = "",
    val seller:String = "",
    val totalPrice:Double = 0.0,
    val detail:List<ProductTransaction> = listOf(),
    val createdAt:Long = 0,
    val updatedAt:Long = 0
)

data class ProductTransaction(
    val uid:String = "",
    val productUid:String = "",
    val quantity:String = "",
    val price:String = "",
    val total:Double = 0.0,
    val createdAt:Long = 0,
    val updatedAt:Long = 0
)

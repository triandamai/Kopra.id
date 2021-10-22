package com.trian.domain.models
/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
data class Store(
    val uid:String,
    val tenantUid:String,
    val storeName:String,
    val description:String,
    val addressStore: String,
    val phoneNumber:Long,
    val logo:String,
    val latitude:Long,
    val longitude:Long,
    val createdAt:Long,
    val updatedAt:Long
)

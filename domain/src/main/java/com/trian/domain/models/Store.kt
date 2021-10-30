package com.trian.domain.models
/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
data class Store(
    val uid:String = "",
    val tenantUid:String = "",
    val storeName:String = "",
    val description:String = "",
    val addressStore: String = "",
    val phoneNumber:String = "",
    val logo:String = "",
    val latitude:Long =0,
    val longitude:Long = 0,
    val createdAt:Long = 0,
    val updatedAt:Long =0
)

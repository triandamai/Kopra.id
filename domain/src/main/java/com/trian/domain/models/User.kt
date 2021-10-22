package com.trian.domain.models

/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
enum class LevelUser{
    TENANT,
    COLLECTOR,
    FARMER
}

data class User(
    val uid:String,
    val phoneNumber:Long,
    val fullName:String,
    val address:String,
    val latitude:Long,
    val longitude:Long,
    val levelUser: LevelUser,
    val createdAt:Long,
    val updatedAt:Long
)

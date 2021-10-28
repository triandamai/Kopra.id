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
    var uid:String = "",
    var phoneNumber:String = "",
    var fullName:String = "",
    var address:String = "",
    var latitude:Long = 0,
    var longitude:Long =0,
    var levelUser: LevelUser = LevelUser.FARMER,
    var createdAt:Long = 0,
    var updatedAt:Long = 0
)

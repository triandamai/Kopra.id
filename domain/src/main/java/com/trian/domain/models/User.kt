package com.trian.domain.models

import com.trian.common.utils.utils.CollectionUtils
import com.trian.common.utils.utils.LevelUser

/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */


data class User(
    var uid:String = "",
    var phoneNumber:String = "",
    var fullName:String = "",
    var username:String="",
    var address:String = "",
    var ttl:Long=0,
    var latitude:Long = 0,
    var longitude:Long =0,
    var profilePicture:String = "",
    var levelUser: LevelUser = LevelUser.UNKNOWN,
    var createdAt:Long = 0,
    var updatedAt:Long = 0
)
fun User.checkShouldUpdateProfile():Boolean{
    return this.fullName == CollectionUtils.NO_DATA_DEFAULT ||
            this.address == CollectionUtils.NO_DATA_DEFAULT ||
            this.ttl == CollectionUtils.DEFAULT_NULL.toLong()     ||
            this.levelUser == LevelUser.UNKNOWN
}
fun User.toUpdatedData():Map<String,Any>{
    val data = mutableMapOf<String,Any>()

    if(this.phoneNumber != "" || this.phoneNumber != CollectionUtils.NO_DATA_DEFAULT) {
        data["phoneNumber"] = this.phoneNumber
    }
    if(this.fullName != "" || this.fullName != CollectionUtils.NO_DATA_DEFAULT) {
        data["fullName"] = this.fullName
    }

    if(this.username != "" || this.username != CollectionUtils.NO_DATA_DEFAULT) {
        data["username"] = this.username
    }

    if( this.ttl != CollectionUtils.DEFAULT_NULL.toLong()) {
        data["ttl"] = this.ttl
    }

    if(this.address != "" || this.address != CollectionUtils.NO_DATA_DEFAULT) {
        data["address"] = this.address
    }
    if(this.latitude.toInt() != 0) {

        data["latitude"] = this.latitude
    }
    if(this.longitude.toInt() !=0) {

        data["longitude"] = this.longitude
    }
    if(this.levelUser != LevelUser.UNKNOWN) {
        data["levelUser"] = this.levelUser
    }

    if(this.profilePicture != "" || this.profilePicture != CollectionUtils.NO_DATA_DEFAULT) {
        data["profilePicture"] = this.profilePicture
    }
    if(this.createdAt.toInt() != 0) {

        data["createdAt"] = this.createdAt
    }
    if(this.updatedAt.toInt() != 0) {

        data["updatedAt"] = this.updatedAt
    }

    return data
}
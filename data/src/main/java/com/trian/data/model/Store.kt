package com.trian.data.model

import com.trian.data.remote.CollectionUtils


/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
enum class TYPE_STORE{
    TENANT,
    COLLECTOR,
    UNKNOWN
}
data class Store(
    var uid:String = CollectionUtils.NO_DATA_DEFAULT,
    var tenantUid:String = CollectionUtils.NO_DATA_DEFAULT,
    var storeName:String = CollectionUtils.NO_DATA_DEFAULT,
    var description:String = CollectionUtils.NO_DATA_DEFAULT,
    var addressStore: String = CollectionUtils.NO_DATA_DEFAULT,
    var phoneNumber:String = CollectionUtils.NO_DATA_DEFAULT,
    var logo:String = CollectionUtils.NO_DATA_DEFAULT,
    var type:TYPE_STORE = TYPE_STORE.UNKNOWN,
    var banner:String = CollectionUtils.NO_DATA_DEFAULT,
    var haveVehicle:Boolean =false,
    var distance:Float =0f,
    var latitude:Double =CollectionUtils.DEFAULT_NULL.toDouble(),
    var longitude:Double = CollectionUtils.DEFAULT_NULL.toDouble(),
    var createdAt:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    var updatedAt:Long =CollectionUtils.DEFAULT_NULL.toLong()
)

fun Store.toUpdatedData():Map<String,Any>{
    val data = mutableMapOf<String,Any>()

    if(this.uid !=  CollectionUtils.NO_DATA_DEFAULT) {
        data["uid"] = this.uid
    }

    if(this.tenantUid !=  CollectionUtils.NO_DATA_DEFAULT) {
        data["tenantUid"] = this.tenantUid
    }
    if(this.storeName != CollectionUtils.NO_DATA_DEFAULT) {

        data["storeName"] = this.storeName
    }
    if(this.description !=  CollectionUtils.NO_DATA_DEFAULT) {

        data["description"] = this.description
    }
    if(this.addressStore != CollectionUtils.NO_DATA_DEFAULT) {

        data["addressStore"] = this.addressStore
    }
    if(this.phoneNumber != CollectionUtils.NO_DATA_DEFAULT) {

        data["phoneNumber"] = this.phoneNumber
    }
    if(this.logo != CollectionUtils.NO_DATA_DEFAULT) {
        data["logo"] = this.logo
    }


    data["haveVehicle"] = this.haveVehicle


    if(this.banner != CollectionUtils.NO_DATA_DEFAULT) {
        data["banner"] = this.banner
    }
    if(this.latitude != CollectionUtils.DEFAULT_NULL.toDouble()) {
        data["latitude"] = this.latitude
    }
    if(this.longitude !=  CollectionUtils.DEFAULT_NULL.toDouble()) {
        data["longitude"] = this.longitude
    }
    if(this.createdAt.toInt() != 0) {

        data["createdAt"] = this.createdAt
    }
    if(this.updatedAt.toInt() != 0) {

        data["updatedAt"] = this.updatedAt
    }

    return data
}
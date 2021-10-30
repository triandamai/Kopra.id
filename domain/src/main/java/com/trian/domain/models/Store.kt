package com.trian.domain.models

import com.trian.common.utils.utils.CollectionUtils

/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
data class Store(
    val uid:String = CollectionUtils.NO_DATA_DEFAULT,
    val tenantUid:String = CollectionUtils.NO_DATA_DEFAULT,
    val storeName:String = CollectionUtils.NO_DATA_DEFAULT,
    val description:String = CollectionUtils.NO_DATA_DEFAULT,
    val addressStore: String = CollectionUtils.NO_DATA_DEFAULT,
    val phoneNumber:String = CollectionUtils.NO_DATA_DEFAULT,
    val logo:String = CollectionUtils.NO_DATA_DEFAULT,
    val banner:String = CollectionUtils.NO_DATA_DEFAULT,
    val latitude:Long =CollectionUtils.DEFAULT_NULL.toLong(),
    val longitude:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    val createdAt:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    val updatedAt:Long =CollectionUtils.DEFAULT_NULL.toLong()
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

    if(this.banner != CollectionUtils.NO_DATA_DEFAULT) {
        data["banner"] = this.banner
    }
    if(this.latitude != CollectionUtils.DEFAULT_NULL.toLong()) {
        data["latitude"] = this.latitude
    }
    if(this.longitude !=  CollectionUtils.DEFAULT_NULL.toLong()) {
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
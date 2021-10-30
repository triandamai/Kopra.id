package com.trian.domain.models

import com.trian.common.utils.utils.CollectionUtils

/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 **/
enum class UnitProduct{
    KG,
    HARI,
    UNKNOWN,
    NO_DATA
}

enum class ProductCategory{
    VEHICLE,
    COMODITI,
    UNKNOWN,
    NO_DATA
}
data class Product(
    val uid:String = CollectionUtils.NO_DATA_DEFAULT,
    val storeUid:String = CollectionUtils.NO_DATA_DEFAULT,
    val productName:String = CollectionUtils.NO_DATA_DEFAULT,
    val category:ProductCategory = ProductCategory.UNKNOWN,
    val price:Double = CollectionUtils.DEFAULT_NULL.toDouble(),
    val thumbnail:String = CollectionUtils.NO_DATA_DEFAULT,
    val unit:UnitProduct = UnitProduct.KG,
    val createdAt:Long = CollectionUtils.NO_DATA_DEFAULT.toLong(),
    val updatedAt:Long = CollectionUtils.NO_DATA_DEFAULT.toLong()
)

fun Product.toUpdatedData():Map<String,Any>{
    val data = mutableMapOf<String,Any>()


    if(this.uid !=  CollectionUtils.NO_DATA_DEFAULT) {

        data["uid"] = this.uid
    }

    if(this.storeUid != CollectionUtils.NO_DATA_DEFAULT) {
        data["storeUid"] = this.storeUid
    }
    if(this.productName != CollectionUtils.NO_DATA_DEFAULT) {

        data["productName"] = this.productName
    }
    if(this.category != ProductCategory.NO_DATA) {

        data["category"] = this.category
    }

    if(this.price != CollectionUtils.DEFAULT_NULL.toDouble()) {

        data["price"] = this.price
    }

    if(this.thumbnail != CollectionUtils.NO_DATA_DEFAULT) {

        data["thumbnail"] = this.thumbnail
    }
    if(this.unit != UnitProduct.NO_DATA) {

        data["unit"] = this.unit
    }

    if(this.createdAt != CollectionUtils.DEFAULT_NULL.toLong()) {
        data["createdAt"] = this.createdAt
    }
    if(this.updatedAt != CollectionUtils.DEFAULT_NULL.toLong()) {

        data["updatedAt"] = this.updatedAt
    }
    return data
}
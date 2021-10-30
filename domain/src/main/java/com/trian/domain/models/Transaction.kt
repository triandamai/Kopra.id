package com.trian.domain.models

import com.trian.common.utils.utils.CollectionUtils

/**
 * Splash Screen Page
 * Author Trian Damai
 * Created by Trian Damai
 * 22/10/2021
 */
enum class StatusTransaction{
    WAITING,
    PROGRESS,
    FINISH,
    CANCELED,
    UNKNOWN,
    NO_DATA
}
data class Transaction(
    var uid:String = CollectionUtils.NO_DATA_DEFAULT,
    var buyerUid:String = CollectionUtils.NO_DATA_DEFAULT,
    var sellerUid:String = CollectionUtils.NO_DATA_DEFAULT,
    var totalPrice:Double =CollectionUtils.DEFAULT_NULL.toDouble(),
    var status:StatusTransaction = StatusTransaction.NO_DATA,
    var detail:List<ProductTransaction> = listOf(),
    var desc:String = CollectionUtils.NO_DATA_DEFAULT,
    var receipt:String =CollectionUtils.NO_DATA_DEFAULT,
    var createdAt:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    var updatedAt:Long = CollectionUtils.DEFAULT_NULL.toLong()
)

data class ProductTransaction(
    var uid:String = "",
    var productUid:String = "",
    var quantity:String = "",
    var price:String = "",
    var total:Double = 0.0,
    var createdAt:Long = 0,
    var updatedAt:Long = 0
)

fun Transaction.toUpdatedData():Map<String,Any>{
    val data = mutableMapOf<String,Any>()

    if(this.uid != CollectionUtils.NO_DATA_DEFAULT) {
        data["uid"] = this.uid
    }
    if(this.buyerUid != CollectionUtils.NO_DATA_DEFAULT) {

        data["buyerUid"] = this.buyerUid
    }
    if(this.sellerUid !=  CollectionUtils.NO_DATA_DEFAULT) {

        data["sellerUid"] = this.sellerUid
    }
    if(this.totalPrice != CollectionUtils.DEFAULT_NULL.toDouble()) {

        data["totalPrice"] = this.totalPrice
    }

    if(this.status != StatusTransaction.NO_DATA) {

        data["status"] = this.status
    }
  //no need detail
    if(this.desc != CollectionUtils.NO_DATA_DEFAULT) {
        data["desc"] = this.desc
    }

    if(this.receipt != CollectionUtils.NO_DATA_DEFAULT) {
        data["receipt"] = this.receipt
    }
    if(this.createdAt.toInt() != 0) {

        data["createdAt"] = this.createdAt
    }
    if(this.updatedAt.toInt() != 0) {

        data["updatedAt"] = this.updatedAt
    }

    return data
}
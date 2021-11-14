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
    PICKUP,
    FINISH,
    CANCELED,
    UNKNOWN,
    NO_DATA
}

data class Transaction(
    var uid:String = CollectionUtils.NO_DATA_DEFAULT,
    var buyerUid:String = CollectionUtils.NO_DATA_DEFAULT,
    var sellerUid:String = CollectionUtils.NO_DATA_DEFAULT,
    var totalPrice:Int =CollectionUtils.DEFAULT_NULL.toInt(),
    var status:StatusTransaction = StatusTransaction.NO_DATA,
    var product:Product=Product(),
    var store:Store=Store(),
    var buyer:User= User(),
    var finishFromSeller:Boolean=false,
    var finishFromBuyer:Boolean = false,
    var desc:String = CollectionUtils.NO_DATA_DEFAULT,
    var receiptSeller:String =CollectionUtils.NO_DATA_DEFAULT,
    var receiptBuyer:String =CollectionUtils.NO_DATA_DEFAULT,
    var createdAt:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    var updatedAt:Long = CollectionUtils.DEFAULT_NULL.toLong()
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
    if(this.totalPrice != CollectionUtils.DEFAULT_NULL.toInt()) {

        data["totalPrice"] = this.totalPrice
    }

    if(this.status != StatusTransaction.NO_DATA) {

        data["status"] = this.status
    }
  //no need detail
    if(this.desc != CollectionUtils.NO_DATA_DEFAULT) {
        data["desc"] = this.desc
    }

    if(this.receiptSeller != CollectionUtils.NO_DATA_DEFAULT) {
        data["receiptSeller"] = this.receiptSeller
    }
    if(this.receiptBuyer != CollectionUtils.NO_DATA_DEFAULT) {
        data["receiptBuyer"] = this.receiptBuyer
    }
    if(this.createdAt.toInt() != 0) {

        data["createdAt"] = this.createdAt
    }
    if(this.updatedAt.toInt() != 0) {

        data["updatedAt"] = this.updatedAt
    }

    return data
}

fun Transaction.toStatusUpdate(status: StatusTransaction):Map<String,Any>{
    val data = mutableMapOf<String,Any>()
    data["status"] = status
    return data

}

fun StatusTransaction.getString(): String {
    return when(this){
        StatusTransaction.WAITING -> "Menunggu Konfirmasi"
        StatusTransaction.PROGRESS -> "Dalam Proses"
        StatusTransaction.PICKUP -> "Proses Penjemputan/Pengantaran"
        StatusTransaction.FINISH -> "Selesai"
        StatusTransaction.CANCELED -> "Dibatalkan"
        StatusTransaction.UNKNOWN -> ""
        StatusTransaction.NO_DATA -> ""
    }
}
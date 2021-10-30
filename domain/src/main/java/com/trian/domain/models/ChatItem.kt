package com.trian.domain.models

import com.trian.common.utils.utils.CollectionUtils
enum class mimeTypeMessage{
    TEXT,
    IMAGE,
    AUDIO,
    VIDEO,
    UNKNOWN,
    NO_DATA
}
data class ChatItem(
    val uid:String = CollectionUtils.NO_DATA_DEFAULT,
    val createdAt:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    val updatedAt:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    val fromUid:String = CollectionUtils.NO_DATA_DEFAULT,
    val toUid:String = CollectionUtils.NO_DATA_DEFAULT,
    val mimeType:mimeTypeMessage = mimeTypeMessage.VIDEO,
    val message:String = CollectionUtils.NO_DATA_DEFAULT,
    val thumb:String = CollectionUtils.NO_DATA_DEFAULT
)

fun ChatItem.toUpdatedData():Map<String,Any>{
    val data = mutableMapOf<String,Any>()


    if(this.fromUid !=  CollectionUtils.NO_DATA_DEFAULT) {

        data["fromUid"] = this.fromUid
    }

    if(this.toUid != CollectionUtils.NO_DATA_DEFAULT) {
        data["toUid"] = this.toUid
    }
    if(this.message.toInt() != 0) {

        data["message"] = this.message
    }
    if(this.thumb.toInt() != 0) {

        data["thumb"] = this.thumb
    }

    if(this.mimeType != mimeTypeMessage.NO_DATA) {

        data["mimeType"] = this.mimeType
    }

    if(this.createdAt != CollectionUtils.DEFAULT_NULL.toLong()) {
        data["createdAt"] = this.createdAt
    }
    if(this.updatedAt != CollectionUtils.DEFAULT_NULL.toLong()) {

        data["updatedAt"] = this.updatedAt
    }
    return data
}
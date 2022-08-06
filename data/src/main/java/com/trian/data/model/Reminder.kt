package com.trian.data.model

import com.trian.data.remote.CollectionUtils


data class Reminder(
    var uid:String = CollectionUtils.NO_DATA_DEFAULT,
    var createdAt:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    var updatedAt:Long = CollectionUtils.DEFAULT_NULL.toLong(),
    var userUid:String = CollectionUtils.NO_DATA_DEFAULT,
    var title:String = CollectionUtils.NO_DATA_DEFAULT,
    var due:Long = CollectionUtils.DEFAULT_NULL.toLong()
)

fun Reminder.toUpdatedData():Map<String,Any>{
    val data = mutableMapOf<String,Any>()

    if(this.userUid != CollectionUtils.NO_DATA_DEFAULT){
        data["userUid"] = this.userUid
    }

    if(this.title != CollectionUtils.NO_DATA_DEFAULT){
        data["title"] = this.title
    }

    if(this.due != CollectionUtils.DEFAULT_NULL.toLong()){
        data["due"] = this.due
    }

    if(this.createdAt != CollectionUtils.DEFAULT_NULL.toLong()) {
        data["createdAt"] = this.createdAt
    }
    if(this.updatedAt != CollectionUtils.DEFAULT_NULL.toLong()) {

        data["updatedAt"] = this.updatedAt
    }
    return data
}
package com.trian.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

@Entity(tableName = "tb_users")
data class User (
    @PrimaryKey(autoGenerate = true)
    var id_user:Int?=null,
    var user_id:String,
    var type: String="unknown",
    var no_type: String="unknown",
    var name: String,
    var username:String,
    var gender: String,
    var email: String,
    var phone_number: String,
    var address: String,
    var thumb:String
)
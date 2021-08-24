package com.trian.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_users")

data class User (
    @PrimaryKey(autoGenerate = true)
    var id_user:Int?,
    var user_id:String?,
    var type: String? ,
    var no_type: String? ,
    var doctor_id:String="kosong",
    var speciality_id:String="kosong",
    var hospital_active:String="kosong",
    var name: String?,
    var username:String?,
    var gender: String?,
    var email: String?,
    var phone_number: String? ,
    var address: String? ,
    var thumb:String?
)
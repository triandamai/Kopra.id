package com.trian.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_measurement")
data class Measurement(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var id_patient:String?,
    var device_id:String?,
    var type:Int?,
    var result:String?,
    var asset:String?,
    var created_at:String?,
    var test_method:String?,
    var timestamp: Long?,
    var is_upload:Boolean?,
    )
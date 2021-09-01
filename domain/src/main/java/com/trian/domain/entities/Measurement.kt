package com.trian.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

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
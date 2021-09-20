package com.trian.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

@Entity(tableName = "tb_measurement")
data class Measurement(
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,
    var member_id:String,
    var nurse_id:String,
    var device_id:String,
    var type:Int,
    var value_temperature:Float=0f,
    var value_blood_oxygen:Int=0,
    var value_systole:Int =0,
    var value_diastole:Int =0,
    var value_respiration:Int =0,
    var value_height:Float =0f,
    var value_weight:Float = 0f,
    var value_heart_rate:Int = 0,
    var value_deep_sleep:Long =0,
    var value_light_sleep:Long=0,
    var value_awake_time:Long=0,
    var value_stethoscope:String="",
    var is_upload:Boolean= false,
    var created_at:Long,
    var updated_at:Long
    )
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
    /**
     * bpm(systole/diastole)= 160/78
     * sleep(sleepDuration(h/m),wakeTime(h/m),fallSleep(hh:mm),awakeTime(hh:mm)) =
     * 8/30/5/20/08:16/09:20
     * respiration(value(time/minute)) = 19
     * blood oxygen(value(%))
     * temperature(value(C))
     * heart rate(value(bpm))
     *
     * **/
    var value:String="0",
    var method:String="automatic",
    var is_upload:Boolean= false,
    var created_at:Long,
    var updated_at:Long
    )
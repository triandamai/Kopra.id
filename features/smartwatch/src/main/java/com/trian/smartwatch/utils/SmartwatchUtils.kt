package com.trian.smartwatch.utils

import com.trian.common.utils.sdk.SDKConstant
import com.trian.domain.entities.Measurement
/**
* Smartwatch Extract data
* Author PT Cexup Telemedicine
* Created by Trian Damai
* 01/09/2021
*/

object HISTORY{
    const val RESP_TEMP_SPO2 = 0x0509
    const val STEP = 0x0502
    const val SLEEP = 0x0504
    const val HR = 0x0506
    const val BPM = 0x0508
}
fun HashMap<*,*>.extractSleepMonitor(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this.get("startTime") as Long
    val endtTime = this.get("endTime") as Long

    val deepSleepCount = this.get("deepSleepCount") as Int
    val deepSleepTotal = this.get("deepSleepTotal") as Int
    val lightSleepCount = this.get("lightSleepCount") as Int
    val lightSleepTotal = this.get("lightSleepTotal") as Int
    val totalHours = ((lightSleepTotal + deepSleepTotal) / 60).toDouble()
    val totalMinutes = totalHours % 60

    return Measurement(
        member_id = user_id,
        nurse_id = "kosong",
        device_id = mac,
        type=SDKConstant.TYPE_SLEEP,
        value_sleep_deep_count = deepSleepCount,
        value_sleep_deep_total = deepSleepTotal,
        value_sleep_light_count = lightSleepCount,
        value_sleep_light_total = lightSleepTotal,
        value_sleep_hours = totalHours,
        value_sleep_minutes = totalMinutes,
        value_sleep_start = startTime,
        value_sleep_end = endtTime,
        created_at = startTime,
        updated_at = startTime
    )
}

fun HashMap<*,*>.extractHeartRate(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this.get("heartStartTime") as Long
    val hr = this.get("heartValue") as Int

    return Measurement(
        member_id = user_id,
        device_id = mac,
        nurse_id = "kosong",
        type = SDKConstant.TYPE_BLOOD_PRESSURE,
        value_heart_rate = hr,
        created_at = startTime,
        updated_at = startTime
    )
}

fun HashMap<*,*>.extractBloodPressure(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this.get("bloodStartTime") as Long
    val systole = this.get("bloodDBP") as Int
    val diastole = this.get("bloodSBP") as Int

    return Measurement(
        member_id = user_id,
        device_id = mac,
        nurse_id = "kosong",
        type = SDKConstant.TYPE_BLOOD_PRESSURE,
        value_systole = systole,
        value_diastole = diastole,
        created_at = startTime,
        updated_at = startTime
    )
}

fun HashMap<*,*>.extractRespiration(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this.get("startTime") as Long
    val respiratoryRateValue = this.get("respiratoryRateValue") as Int // if (respiratoryRateValue == 0)  no value

    return if(respiratoryRateValue == 0) return null else
        Measurement(
            member_id = user_id,
            device_id = mac,
            nurse_id="kosong",
            type = SDKConstant.TYPE_RESPIRATION,
            value_respiration = respiratoryRateValue,
            created_at = startTime,
            updated_at = startTime
        )
}

fun HashMap<*,*>.extractTemperature(
    user_id:String,
    mac:String
):Measurement?{
    var temp = 0f
    val startTime = this.get("startTime") as Long
    val tempIntValue = this.get("tempIntValue") as Int //Temp int value

    val tempFloatValue = this.get("tempFloatValue") //if (tempFloatValue == 15) the result is error

    if (tempFloatValue != 15) {
        temp = "$tempIntValue.$tempFloatValue".toFloat()
    }
    return if (tempFloatValue == 15 || temp < 1) null else
       Measurement(
           member_id = user_id,
           nurse_id="kosong",
           device_id = mac,
           type = SDKConstant.TYPE_TEMPERATURE,
           value_temperature = temp,
           created_at = startTime,
           updated_at = startTime
       )

}

fun HashMap<*,*>.extractBloodOxygen(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this.get("startTime") as Long
    val blood_oxygen = this.get("OOValue") as Int // if (blood_oxygen == 0)  no value

    return if (blood_oxygen < 1) null else
        Measurement(
            member_id = user_id,
            nurse_id="kosong",
            device_id = mac,
            type = SDKConstant.TYPE_BLOOD_OXYGEN,
            value_blood_oxygen = blood_oxygen,
            created_at = startTime,
            updated_at = startTime
        )

}
package com.trian.data.utils

import android.icu.util.Measure
import android.util.Log
import com.trian.common.utils.analytics.analyzeBPM
import com.trian.common.utils.sdk.SDKConstant
import com.trian.domain.entities.Measurement
import com.trian.domain.models.BloodPressureModel
import com.trian.domain.models.SleepModel
import java.util.*
import kotlin.collections.HashMap

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
    /*
    startTime - sleep end time
    'endTime' - sleep end time,
     'deepSleepCount' - number of deep sleeps,
     'lightSleepCount' - number of light sleeps,
     'deepSleepTotal' - total time of deep sleep in minutes,
     'lightSleepTotal'- -Total light sleep time in minutes,
     'sleepData'-detailed sleep data set,
        'sleepType-
        0xF1: deep sleep
        0xF2: light sleep,
        'sleepStartTime'-start timestamp,
        'sleepLen'-sleep duration in seconds
    */
    Log.e("RESULT SLEEP",this.toString())
    val startTime = this["startTime"] as Long
    val endtTime = this["endTime"] as Long

    val deepSleepCount = this["deepSleepCount"] as Int
    val deepSleepTotal = this["deepSleepTotal"] as Int
    val lightSleepCount = this["lightSleepCount"] as Int
    val lightSleepTotal = this["lightSleepTotal"] as Int
    val totalHours = ((lightSleepTotal + deepSleepTotal) / 60).toDouble()
    val totalMinutes = totalHours % 60

    /**
    * sleep(sleepDuration(h/m),wakeTime(h/m),fallSleep(hh:mm),awakeTime(hh:mm)) =
    * 8/30/5/20/08:16/09:20
    * */
    return Measurement(
        member_id = user_id,
        nurse_id = "kosong",
        device_id = mac,
        type=SDKConstant.TYPE_SLEEP,
        value="$totalHours/$totalMinutes/$",
        created_at = startTime,
        updated_at = startTime
    )
}
fun HashMap<*,*>.extractStep(
    user_id:String,
    mac:String
){
    val startTime = this["sportStartTime"] as Long
    val endTime = this["sportEndTime"] as Long
    val sportStep = this["sportStep"] as Int
    val sportCalorie = this["sportCalorie"] as Double
    val sportDistance = this["sportDistance"] as Double
}
fun HashMap<*,*>.extractHeartRate(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this["heartStartTime"] as Long
    val hr = this["heartValue"] as Int

    return Measurement(
        member_id = user_id,
        device_id = mac,
        nurse_id = "kosong",
        type = SDKConstant.TYPE_HEARTRATE,
        value = "$hr",
        created_at = startTime,
        updated_at = startTime
    )
}

fun HashMap<*,*>.extractBloodPressure(
    user_id:String,
    mac:String
):Measurement?{

    val startTime = this["bloodStartTime"] as Long
    val systole = this["bloodDBP"] as Int
    val diastole = this["bloodSBP"] as Int

    return Measurement(
        member_id = user_id,
        device_id = mac,
        nurse_id = "kosong",
        type = SDKConstant.TYPE_BLOOD_PRESSURE,
        value = "$systole/$diastole",
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
            value = "$respiratoryRateValue",
            created_at = startTime,
            updated_at = startTime
        )
}

fun HashMap<*,*>.extractTemperature(
    user_id:String,
    mac:String
):Measurement?{

    var temp = 0f
    val startTime = this["startTime"] as Long
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
           value = "$temp",
           created_at = startTime,
           updated_at = startTime
       )

}

fun HashMap<*,*>.extractBloodOxygen(
    user_id:String,
    mac:String
):Measurement?{
    val id = UUID.randomUUID()
    val startTime = this["startTime"] as Long
    val blood_oxygen = this.get("OOValue") as Int// if (blood_oxygen == 0)  no value

    return if (blood_oxygen < 1) null else
        Measurement(
            member_id = user_id,
            nurse_id="kosong",
            device_id = mac,
            type = SDKConstant.TYPE_BLOOD_OXYGEN,
            value = "$blood_oxygen",
            created_at = startTime,
            updated_at = startTime
        )

}

/**
 *
 * get min max
 * this extension allow us to calculate which the latest,min,or max data from list given
 * @return onResult(DSL)
 * */
fun List<Measurement>.calculateMaxMin(onResult:(empty:Boolean,latest:Measurement?,max:Measurement?,min:Measurement?)->Unit){
    var maxindexed=0
    var minindexed=6

    var latest:Measurement?= null
    var max:Measurement?=null
    var min:Measurement?=null
    if(this.isNotEmpty()) {
        this.forEachIndexed { index, measurement ->
            if (index == 0) {
                latest = measurement
                max = measurement
                min = measurement
            }
            if (latest!!.created_at <= measurement.created_at) {
                latest = measurement
            }
            when (measurement.type) {
                SDKConstant.TYPE_SLEEP->{
                    val sleep = measurement.value.explodeSleep()
                }
                SDKConstant.TYPE_BLOOD_PRESSURE -> {
                    val bpm = measurement.value.explodeBloodPressure()

                    val result = analyzeBPM(bpm.systole, bpm.diastole)
                    if (index == 0) {
                        maxindexed = result.status
                    }

                    if (maxindexed <= result.status) {
                        val maxBpm = max!!.value.explodeBloodPressure()

                        if (
                            maxBpm.systole < bpm.systole ||
                            maxBpm.diastole < bpm.diastole
                        ) {
                            maxindexed = result.status
                            max = measurement
                        }
                    }
                    if (minindexed >= result.status) {
                        val minBpm = min!!.value.explodeBloodPressure()
                        if (
                            minBpm.systole > bpm.systole ||
                            minBpm.diastole > bpm.diastole
                        ) {
                            minindexed = result.status
                            min = measurement
                        }
                    }
                }
                SDKConstant.TYPE_HEARTRATE -> {
                    if (measurement.value.toInt() >= max!!.value.toInt()) {
                        max = measurement
                    }
                    if (measurement.value.toInt()<= min!!.value.toInt()) {
                        min = measurement
                    }
                }
                SDKConstant.TYPE_RESPIRATION -> {

                    if (max!!.value.toInt() < measurement.value.toInt()) {
                        max = measurement
                    }
                    if (min!!.value.toInt() > measurement.value.toInt()) {
                        min = measurement
                    }
                }
                SDKConstant.TYPE_TEMPERATURE -> {
                    if (measurement.value.toFloat() > max!!.value.toFloat()) {
                        max = measurement
                    }
                    if (measurement.value.toFloat()< min!!.value.toFloat()) {
                        min = measurement
                    }
                }
                SDKConstant.TYPE_BLOOD_OXYGEN -> {

                    if (measurement.value.toInt() > max!!.value.toInt()) {
                        max = measurement
                    }
                    if (measurement.value.toInt() < min!!.value.toInt()) {
                        min = measurement
                    }
                }
            }

        }.also {
            onResult(false,latest, max, min)
        }
    }else{
        onResult(true,latest,max,min)
    }
}


fun String.explodeBloodPressure():BloodPressureModel{
    val result=this.split("/").toTypedArray()

    return BloodPressureModel(
        systole = result[0].toInt(),
        diastole = result[1].toInt()
    )
}
fun String.explodeSleep():SleepModel{
    val result = this.split("/")
    return SleepModel(
        sleepDuration = result[0].toDouble(),
        wakeTime = result[1].toInt(),
        fallSleepTime = result[2],
        awakeTime = result[3]
    )
}
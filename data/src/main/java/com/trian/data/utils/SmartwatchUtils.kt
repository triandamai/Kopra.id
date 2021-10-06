package com.trian.data.utils

import android.util.Log
import com.google.gson.Gson
import com.trian.common.utils.analytics.analyzeBPM
import com.trian.common.utils.sdk.SDKConstant
import com.trian.common.utils.utils.formatHoursMinute
import com.trian.domain.entities.Measurement
import com.trian.domain.models.BloodPressureModel
import com.trian.domain.models.SleepDatum
import com.trian.domain.models.SleepModel
import com.trian.domain.models.StepModel
import java.util.*
import kotlin.collections.HashMap

/**
* Smartwatch Extract data
* Author PT Cexup Telemedicine
* Created by Trian Damai
* 01/09/2021
*/

fun HashMap<*,*>.extractSleepMonitor(
    user_id:String,
    mac:String,
    gson: Gson = Gson()
):Measurement?{
    try {


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

        val startTime = this["startTime"] as Long
        val endTime = this["endTime"] as Long
        val deepSleepCount = this["deepSleepCount"] as Int
        val deepSleepTotal = this["deepSleepTotal"] as Int
        val lightSleepCount = this["lightSleepCount"] as Int
        val lightSleepTotal = this["lightSleepTotal"] as Int
     //   val totalHours = ((lightSleepTotal + deepSleepTotal) / 60).toDouble()
       // val totalMinutes = totalHours % 60
        val sleepData: ArrayList<java.util.HashMap<*, *>> =
            this["sleepData"] as ArrayList<java.util.HashMap<*, *>>


        val sleepDatas = sleepData.map {
            it.extractSleepData()
        }


        val model = SleepModel(
            deepSleepCount = deepSleepCount,
            deepSleepTotal = deepSleepTotal,
            lightSleepCount = lightSleepCount,
            lightSleepTotal = lightSleepTotal,
            startTime = startTime,
            endTime = endTime,
            sleepData = sleepDatas
        )

        /**
         * sleep(sleepDuration(h/m),wakeTime(h/m),fallSleep(hh:mm),awakeTime(hh:mm)) =
         * 8/30/5/20/08:16/09:20
         * */
        return Measurement(
            member_id = user_id,
            nurse_id = "kosong",
            device_id = mac,
            type = SDKConstant.TYPE_SLEEP,
            value = gson.toJson(model),
            created_at = startTime,
            end_at = endTime,
            updated_at = startTime
        )
    }catch (e:Exception){
        e.printStackTrace()
        Log.e("EXTRACT SLEEP",e.stackTraceToString())
        return null
    }
}
internal fun HashMap<*,*>.extractSleepData():SleepDatum{
    val sleepStartTime = this["sleepStartTime"] as Long
    val sleepLen = this["sleepLen"] as Int
    val sleepType = this["sleepType"] as Int
    return SleepDatum(
        sleepType = sleepType,
        sleepLen = sleepLen,
        sleepStartTime = sleepStartTime
    )
}
fun HashMap<*,*>.extractStep(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this["sportStartTime"] as Long
    val endTime = this["sportEndTime"] as Long
    val sportStep = this["sportStep"] as Int
    val sportCalorie = this["sportCalorie"] as Double
    val sportDistance = this["sportDistance"] as Double

    return Measurement(
        member_id = user_id,
        nurse_id = "kosong",
        device_id = mac,
        type = SDKConstant.TYPE_STEP,
        value = "$sportStep/$sportCalorie/$sportDistance",
        created_at = startTime,
        end_at = endTime,
        updated_at = startTime
    )
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
        end_at=startTime,
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
        end_at=startTime,
        updated_at = startTime
    )
}

fun HashMap<*,*>.extractRespiration(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this["startTime"] as Long
    val respiratoryRateValue = this["respiratoryRateValue"] as Int // if (respiratoryRateValue == 0)  no value

    return if(respiratoryRateValue == 0) return null else
        Measurement(
            member_id = user_id,
            device_id = mac,
            nurse_id="kosong",
            type = SDKConstant.TYPE_RESPIRATION,
            value = "$respiratoryRateValue",
            created_at = startTime,
            end_at=startTime,
            updated_at = startTime
        )
}

fun HashMap<*,*>.extractTemperature(
    user_id:String,
    mac:String
):Measurement?{

    var temp = 0f
    val startTime = this["startTime"] as Long
    val tempIntValue = this["tempIntValue"] as Int //Temp int value

    val tempFloatValue = this["tempFloatValue"] //if (tempFloatValue == 15) the result is error

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
           end_at=startTime,
           updated_at = startTime
       )

}

fun HashMap<*,*>.extractBloodOxygen(
    user_id:String,
    mac:String
):Measurement?{
    val startTime = this["startTime"] as Long
    val bloodOxygen = this["OOValue"] as Int// if (blood_oxygen == 0)  no value

    return if (bloodOxygen < 1) null else
        Measurement(
            member_id = user_id,
            nurse_id="kosong",
            device_id = mac,
            type = SDKConstant.TYPE_BLOOD_OXYGEN,
            value = "$bloodOxygen",
            created_at = startTime,
            end_at=startTime,
            updated_at = startTime
        )

}

/**
 *
 * **/
fun Measurement.calculateSleepSummary(
    onResult:(
        totalDuration:String,
        deepSleep:String,
        lightSleep:String,
        wakeTime:String,
        fallSleepTime:String,
        awakeTime:String
    )->Unit
) {

//                    val totalHours = ((sleep.lightSleepTotal + sleep.deepSleepTotal) / 60).toDouble()
//                    val totalMinutes = totalHours % 60

    val sleep = this.value.explodeSleep()
    //calculate duration
    val totalHours = ((sleep.lightSleepTotal + sleep.deepSleepTotal) / 60)
    val totalMinutes = totalHours % 60

    //deep duration
    val totalHoursDeepSleep = (sleep.deepSleepTotal/60)
    val totalMinutesDeepSleep = totalHoursDeepSleep % 60

    //light duration
    val totalHoursLightSleep = (sleep.lightSleepTotal/60)
    val totalMinutesLightSleep = totalHoursLightSleep % 60

    val wakeTimes = sleep.sleepData.map { if (it.sleepType == 242)  1 else  0}.reduce { acc, i -> acc+i }

    val fallSleep = sleep.startTime.formatHoursMinute()
    val awakeSleep = sleep.endTime.formatHoursMinute()
 //   0xF2 = deep == 242
    onResult(
        "$totalHours.$totalMinutes",
        "$totalHoursDeepSleep.$totalMinutesDeepSleep",
        "$totalHoursLightSleep.$totalMinutesLightSleep",
        wakeTimes.toString(),
        fallSleep,
        awakeSleep
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

fun String.explodeStep():StepModel{
    //value = "$sportStep/$sportCalorie/$sportDistance",
    val result=this.split("/").toTypedArray()

    return StepModel(
        sportStep = result[0].toInt(),
        sportCalorie = result[1].toDouble(),
        sportDistance = result[2].toDouble(),
    )
}

fun String.explodeSleep():SleepModel{
    val gson = Gson()
    return gson.fromJson(this,SleepModel::class.java)
}
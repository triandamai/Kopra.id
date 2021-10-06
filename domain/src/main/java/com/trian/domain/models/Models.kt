package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class BloodOxygenModel(var spo2:Int = 0)
data class BloodPressureModel(var systole:Int =0, var diastole:Int = 0)
data class HeightModel(var height:Float = 0f)
data class WeightModel (var  weight:Float =0f)
data class BodyMassIndexModel(var weight: Float =0f,)
data class WaistModel(var waist:Float =0f)
data class TemperatureModel(var temperature:Float =0f)
data class RespirationModel(var breath:Int=0)
/**
 * sleep(sleepDuration(h/m),wakeTime(time),fallSleep(hh:mm),awakeTime(hh:mm)) =
 * 8/30/2/08:16/09:20
 * */
data class SleepModel (
    val deepSleepCount: Int,
    val lightSleepCount: Int,
    val sleepData: List<SleepDatum>,
    val startTime: Long,
    val lightSleepTotal: Int,
    val endTime: Long,
    val deepSleepTotal: Int
)
data class SleepDatum (
    val sleepStartTime: Long,
    val sleepLen: Int,
    val sleepType: Int
)
data class HeartRateModel(var hrperminute:Int =0)
data class StethoscopeModel(var position: String, var filename: String)
data class CardioGraphModel(var value:String)
data class StepModel(
                       val sportStep: Int=0,
                       val sportCalorie: Double=0.0,
                       val sportDistance : Double =0.0)


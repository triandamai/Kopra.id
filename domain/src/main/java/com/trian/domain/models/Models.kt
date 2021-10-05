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
data class SleepModel(var sleepDuration:Double,var wakeTime:Int,var fallSleepTime:String,var awakeTime:String)
data class HeartRateModel(var hrperminute:Int =0)
data class StethoscopeModel(var position: String, var filename: String)
data class CardioGraphModel(var value:String)


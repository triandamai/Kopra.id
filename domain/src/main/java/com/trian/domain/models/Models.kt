package com.trian.domain.models


data class BloodOxygenModel(var spo2:Float = 0f, var hr:Float =0f,var method: String ="automatic",var timestamp: Long=0)
data class BloodPressureModel(var systole:Float =0f, var diastole:Float = 0f, var pulse:Float=0f,var method: String ="automatic",var timestamp: Long=0)
data class HeightModel(var height:Float = 0f,var method:String = "automatic",var timestamp: Long=0)
data class WeightModel (var  weight:Float =0f, var method:String = "automatic",var timestamp: Long=0)
data class BMIModel(var weight: Float =0f,var height: Float=0f,var bmi:Float=0f,var method: String ="automatic",var timestamp: Long=0)
data class WaistModel(var waist:Float =0f,var method:String = "automatic",var timestamp: Long=0)
data class TemperatureModel(var temperature:Float =0f,var method: String ="automatic",var timestamp: Long = 0)
data class RespirationModel(var breathPerminute:Int=0,var method: String ="automatic",var timestamp: Long=0);
data class SleepModel(var hours:Int=0,var minutes:Int=0,var lightSleepCount:Int=0,var deepSleepCount:Int=0,var method: String ="automatic",var timestamp: Long=0)
data class HeartRateModel(var hrperminute:Float =0f,var method: String ="automatic",var timestamp: Long=0)
data class StethoscopeModel(var position: String, var filename: String, var dot: String,var method: String ="automatic",var timestamp: Long=0)


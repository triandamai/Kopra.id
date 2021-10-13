package com.trian.common.utils.analytics

/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 06/10/2021
 **/

class EarlyWarningScore(
    val respiration:Int,
    val systole:Int,
    val temperature:Float,
    val bloodOxygen:Int,
    val pulse:Int,
    val aware:Boolean,
    val oxygen:Boolean,
    private var temp: MutableList<Int> = arrayListOf()
){


    fun getResult():EwsResult{
         var isDanger:Boolean=false
        val aware = if(aware) {3}else{0}
        val oxygen = if(oxygen) {3}else{0}
        temp.add(bloodOxygen.calculateBloodOxygen())
        temp.add(temperature.calculateTemperature())
        temp.add(pulse.calculatePulse())
        temp.add(systole.calculateSystole())
        temp.add(respiration.calculateRespiration())
        temp.add(aware)
        temp.add(oxygen)

        isDanger = temp.contains(3)
        val result = temp.reduce { acc, i ->
            acc+i
        }

        if(result < 1) return EwsResult(0,"")
        if(result in 1..4) {
            if(isDanger) return EwsResult(2,"Warning")
            return EwsResult(1,"Normal")
        }
        if(result in 5..6) return EwsResult(2,"Warning")

        return EwsResult(3,"Danger")
    }

}

internal fun Int.calculateRespiration():Int{
    if(this ==0)return 0
    if(this <= 8 || this >= 25)return 3
    if(this in 9..11)return 1
    if(this in 12..20)return 0
    if(this in 21..24)return 2

    return 0
}
internal fun Float.calculateTemperature():Int{
    if(this == 0f) return 0
    if(this <= 35.0f) return 3
    if(this in 35.1..36.0) return 1
    if(this in 36.1..38.0) return 0
    if(this in 38.1..39.1)return 1
    if(this >= 39.2) return 2
    return 0
}

internal fun Int.calculateSystole():Int{
    if(this ==0)return 0
    if(this <= 90 || this >=220) return 3
    if(this in 91..100 ) return 2
    if(this in 101..110) return 1
    if(this in 111..219) return 0
    return 0
}
internal fun Int.calculatePulse():Int{
    if(this == 0) return 0
    if(this <=40 || this >= 131) return 3
    if(this <= 50) return 1
    if(this in 51..90) return 0
    if(this in 91..110) return 1
    if(this in 111..130) return 2
    return 0
}
internal fun Int.calculateBloodOxygen():Int{
    if(this ==0) return 3
    if(this <= 91 )return 3
    if(this >=96 )return 0
    if(this in 92..93)return 2
    if(this in 94..95)return 1
    return 0
}
data class EwsResult(val level:Int,val result:String)
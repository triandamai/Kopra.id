package com.trian.common.utils.analytics

/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 06/10/2021
 **/

class EarlyWarningScore(
    private val respiration:Int,
    val systole:Int,
    val temperature:Float,
    private val bloodOxygen:Int,
    private val pulse:Int,
    private val aware:Boolean,
    private val oxygen:Boolean,
    private var temp: MutableList<IndividualResult> = arrayListOf()
){


    fun getResult():EwsResult{
        val aware = if(aware) {
            IndividualResult(3,"Aware")
        }else{
            IndividualResult(0,"")
        }
        val oxygen = if(oxygen) {
            IndividualResult(3,"Use Oxygen")
        }else{
            IndividualResult(0,"")
        }
        temp.add(bloodOxygen.calculateBloodOxygen())
        temp.add(temperature.calculateTemperature())
        temp.add(pulse.calculatePulse())
        temp.add(systole.calculateSystole())
        temp.add(respiration.calculateRespiration())
        temp.add(aware)
        temp.add(oxygen)

        val point =  temp.map { it.point }
        val due = temp.map { it.due }
        val result = point.reduce { acc, i ->
            acc+i
        }
        val isDanger = point.contains(3)

        if(result < 1) return EwsResult(0,"",due)
        if(result in 1..4) {
            if(isDanger) return EwsResult(2,"Warning",due)
            return EwsResult(1,"Normal",due)
        }
        if(result in 5..6) return EwsResult(2,"Warning",due)

        return EwsResult(3,"Danger",due)
    }

}

internal fun Int.calculateRespiration():IndividualResult{
    if(this ==0)return IndividualResult(0,"")
    if(this <= 8 || this >= 25)return IndividualResult(3,"Respiration $this")
    if(this in 9..11)return IndividualResult(1,"Respiration $this")
    if(this in 12..20)return IndividualResult(0,"")
    if(this in 21..24)return IndividualResult(2,"Respiration $this")

    return IndividualResult(0,"")
}
internal fun Float.calculateTemperature():IndividualResult{
    if(this == 0f) return IndividualResult(0,"")
    if(this <= 35.0f) return IndividualResult(3,"Temperature $this")
    if(this in 35.1..36.0) return IndividualResult(1,"Temperature $this")
    if(this in 36.1..38.0) return IndividualResult(0,"")
    if(this in 38.1..39.1)return IndividualResult(1,"Temperature $this")
    if(this >= 39.2) return IndividualResult(2,"Temperature $this")
    return IndividualResult(0,"")
}

internal fun Int.calculateSystole():IndividualResult{
    if(this ==0)return IndividualResult(0,"")
    if(this <= 90 || this >=220) return IndividualResult(3,"Systole $this")
    if(this in 91..100 ) return IndividualResult(2,"Systole $this")
    if(this in 101..110) return IndividualResult(1,"Systole $this")
    if(this in 111..219) return IndividualResult(0,"")
    return IndividualResult(0,"")
}
internal fun Int.calculatePulse():IndividualResult{
    if(this == 0) return IndividualResult( 0,"")
    if(this <=40 || this >= 131) return IndividualResult( 3,"Pulse $this")
    if(this <= 50) return IndividualResult(1,"Pulse $this")
    if(this in 51..90) return IndividualResult(0,"")
    if(this in 91..110) return IndividualResult(1,"Pulse $this")
    if(this in 111..130) return IndividualResult(2,"Pulse $this")
    return IndividualResult( 0,"")
}
internal fun Int.calculateBloodOxygen():IndividualResult{
    if(this ==0) return IndividualResult( 3,"Blood Oxygen $this")
    if(this <= 91 )return IndividualResult( 3,"Blood Oxygen $this")
    if(this >=96 )return IndividualResult( 0,"")
    if(this in 92..93)return IndividualResult( 2,"Blood Oxygen $this")
    if(this in 94..95)return IndividualResult( 1,"Blood Oxygen $this")
    return IndividualResult( 0,"")
}
data class IndividualResult(val point:Int,val due:String)
data class EwsResult(val level:Int, val result:String, val due: List<String>)
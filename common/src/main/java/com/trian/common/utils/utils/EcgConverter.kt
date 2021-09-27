package com.trian.component.utils

import android.util.Log
import java.util.ArrayList


/**
 * Ecg wave
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * Created At 27/09/2021
 */
//https://stackoverflow.com/questions/31753062/calculate-heart-rate-from-ecg-stream-java-nymi-band
fun correlate(data:Array<Float>,nElementsSize:Int,offset:Int):Float{
    var summ:Float=0f

    for (i in 0 until nElementsSize -offset){
        summ += data[i] * data[i+offset]
    }
    return summ
}

fun getBeat(data:List<Float>,n:Int):Int{
    var c = mutableListOf<Float>()

    var minElement=0
    var maxElement:Int
    var minValue:Float
    var maxValue:Float

    // calculate the time-delayed correlation of the signal with itself:
    for (i in 0 until n){
        c.add(correlate(data = data.toTypedArray(),n,i))
    }

    // Heuristic: Search for the first element that is higher than
    // it's precursor: (this is an heuristic to skip the trivial
    // correlation of the signal with itself).
    minValue = c[0]
    for(i in 1 until n){
        if(c[i] > c[i+1]){
            minValue = c[i]
            minElement =i
            break
        }
    }
    // Now just search for the highest peak. That's
    // where the highest periodicity in the signal is
    // located:
    maxValue = minValue
    maxElement = minElement
    for (i in minElement until n){
        if(c[i] > maxValue){
            maxValue = c[i]
            maxElement =i
        }
    }

    return maxElement
}


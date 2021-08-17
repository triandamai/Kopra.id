package com.cexup_sdk.analytics

import com.cexup_sdk.R


/**c
 *
 * */

fun Float.analyzeTemperature():Result?{

    if(this <=35){
        return Result(R.color.text_blue,"Hypothermia",0)
    }
    if(this in 35.5..37.5){

        return Result(R.color.green,"Normal",0)

    }
    if(this in 37.6..40.0){
        return Result(R.color.text_red,"Hypertehermia",0)
    }
    //
    if(this > 40 ){
        return Result(R.color.red,"Hyperpyrexia",0)
    }
    return Result(R.color.red,"",0);
}

fun Float.analyzeHeartRate():Result?{

    if(this <=50){
        return Result(R.color.text_blue,"Bradycardia",0)
    }
    if(this in 51.0..100.0){
        return Result(R.color.text_green,"Normal",0)
    }
    if(this > 100){
        return Result(R.color.text_red,"Tachycardia",0)
    }
    return Result(R.color.red,"",0);
}

fun Float.analyzeRespiratory():Result?{

    if(this < 12){
        return Result(R.color.text_blue,"Bradypnoea",0)
    }
    if(this in 12.0..20.0){
        return Result(R.color.text_green,"Normal",0)
    }
    if(this > 20){
        return Result(R.color.text_red,"Tachynoea",0)
    }
    return Result(R.color.red,"",0);
}



fun Float.analyzeSpo2():Result?{
    if(this in 0.0..85.0){
        return Result(R.color.text_red,"Severe Hypoxia",0)
    }
    if(this in 86.0..91.0){
        return Result(R.color.text_orange,"Moderate Hypoxia",0)
    }
    if(this in 92.0..94.0){
        return Result(R.color.text_blue,"Mild Hypoxia",0)
    }
    if(this in 95.0..100.0){
        return Result(R.color.text_green,"Normal Saturation",0)
    }
    return Result(R.color.red,"",0)
}


fun analyzeBPM(systole:Int,diastole:Int):Result?{
    if(systole < 120){
        return diastole.checkDia(1)
    }
    if(systole in 120..129){
        return diastole.checkDia(2)
    }
    if(systole in 130..139){
         return diastole.checkDia(3)
    }

    if(systole in 140..159){
        return diastole.checkDia(4)
    }

    if(systole in 160..179){
        return diastole.checkDia(5)
    }

    if(systole >= 180){
        return diastole.checkDia(6)
    }
    return Result(R.color.bg_purple,"Undefined",0)

}
fun Int.checkDia(type:Int):Result?{
    if(this < 80){
        when(type){
            1->{
                return Result(R.color.text_green2, "Optimal",1)
            }
            2->{
                return Result(R.color.text_green, "Normal",2)
            }
            3->{
                return Result(R.color.text_orange, "Normal High",3)

            }
            4->{
                return Result(R.color.text_red, "Hypertension Grade 1",4)

            }
            5->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            6->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
        }
    }
    if(this in 80..84){
        when(type){
            1->{
                return Result(R.color.text_green, "Normal",2)
            }
            2->{
                return Result(R.color.text_green, "Normal",2)
            }
            3->{
                return Result(R.color.text_orange, "Normal High",3)
            }
            4->{
                return Result(R.color.text_red, "Hypertension Grade 1",4)
            }
            5->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            6->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }


        }
    }
    if(this in 85..89){
        when(type){
            1->{
                return Result(R.color.text_orange, "Normal High",3)
            }
            2->{
                return Result(R.color.text_orange, "Normal High",3)
            }
            3->{
                return Result(R.color.text_orange, "Normal High",3)
            }
            4->{
                return Result(R.color.text_red, "Hypertension Grade 1",4)
            }
            5->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            6->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
        }
    }
    if(this in 90..99){
        when(type){
            1->{
                return Result(R.color.text_red, "Hypertension Grade 1",4)
            }
            2->{
                return Result(R.color.text_red, "Hypertension Grade 1",4)
            }
            3->{
                return Result(R.color.text_red, "Hypertension Grade 1",4)
            }
            4->{
                return Result(R.color.text_red, "Hypertension Grade 1",4)
            }
            5->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            6 ->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
        }
    }
    if(this in 100..109){
        when(type){
            1->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            2->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            3->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            4->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            5->{
                return Result(R.color.red, "Hypertension Grade 2",5)
            }
            6 ->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
        }
    }

    if(this >= 110){
        when(type){
            1->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
            2->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
            3->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
            4->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
            5 -> {
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }
            6 ->{
                return Result(R.color.redHigh, "Hypertension Grade 3",6)
            }

        }
    }
    return Result(R.color.red,"",0)
}

fun Float.analyzBmi():Result?{
    if (this in 0.0..18.5){
        return Result(R.color.text_blue,"UnderWeight",0)
    }
    if(this in 18.5..25.0){
        return Result(R.color.text_green,"Normal",0)
    }
    if(this in 25.0..30.0){
        return Result(R.color.text_orange,"OverWeight",0)
    }
    if(this in 30.0..40.0){
        return Result(R.color.text_red,"Obesity Grade 1",0)
    }
    if(this > 40){
        return Result(R.color.red,"Obesity Grade 3",0)
    }
    return Result(R.color.red,"",0)
}
data class Result(var color:Int?,var result: String,var status:Any?)
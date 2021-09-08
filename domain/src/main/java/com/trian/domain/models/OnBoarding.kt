package com.trian.domain.models

import com.trian.domain.R

class OnBoarding(
    val title:Int,
    val text:Int,
    val image:Int,
){
    companion object{
        fun get():List<OnBoarding>{
            return listOf(
                OnBoarding(title = R.string.dummyonboardin1,text = R.string.hypertension_grade_1,image = R.drawable.onboard),
                OnBoarding(title = R.string.dummyonboardin1,text = R.string.hypertension_grade_2,image = R.drawable.onboard),
                OnBoarding(title = R.string.dummyonboardin1,text = R.string.hypertension_grade_3,image = R.drawable.onboard)
            )
        }
    }
}
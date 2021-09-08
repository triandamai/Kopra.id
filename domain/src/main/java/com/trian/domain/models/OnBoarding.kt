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
                OnBoarding(title = R.string.dummyonboardin1,text = R.string.dummytes,image = R.drawable.onboard),
                OnBoarding(title = R.string.dummyonboardin1,text = R.string.dummytes,image = R.drawable.onboard),
                OnBoarding(title = R.string.dummyonboardin1,text = R.string.dummytes,image = R.drawable.onboard)
            )
        }
    }
}
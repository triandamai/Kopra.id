package com.trian.common.utils.utils

import android.annotation.SuppressLint
import org.joda.time.DateTime
import java.text.SimpleDateFormat


fun getTodayTimeStamp():Long{
    return DateTime().millis
}

fun getLastdayTimeStamp():Long{
    return DateTime().withTimeAtStartOfDay().millis
}

@SuppressLint("SimpleDateFormat")
fun Long.formatDate():String{
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this)
}

fun String.toMillis():Long{
    return DateTime(this).millis
}

fun getTimeStampFromDate(year:Int,month:Int, day:Int):Long{
    return DateTime(year,month,day,0,0).plus(1).millis
}


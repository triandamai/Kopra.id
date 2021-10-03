package com.trian.common.utils.utils

import android.annotation.SuppressLint
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat


fun getTodayTimeStamp():Long{
    return DateTime().millis
}

fun Long.getPreviousDate():Long{
    return DateTime(this).minusDays(1).withTimeAtStartOfDay().millis
}

fun Long.getNextDate():Long{
    return DateTime(this).plusDays(1).withTimeAtStartOfDay().millis
}

fun getLastDayTimeStamp():Long{
    return DateTime().withTimeAtStartOfDay().millis
}

@SuppressLint("SimpleDateFormat")
fun Long.formatDate():String{
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this)
}

@SuppressLint("SimpleDateFormat")
fun Long.formatHoursMinute():String{
    return SimpleDateFormat("HH:MM").format(this)
}


//https://stackoverflow.com/questions/20331163/how-to-format-joda-time-datetime-to-only-mm-dd-yyyy
fun Long.formatReadableDate():String{
        return DateTime(this).toString(DateTimeFormat.longDate())
}

fun String.toMillis():Long{
    return DateTime(this).millis
}

fun getTimeStampFromDate(year:Int,month:Int, day:Int):Long{
    return DateTime(year,month,day,0,0).plus(1).millis
}


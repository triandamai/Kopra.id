package com.trian.common.utils.sdk

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

object SDKConstant {
    const val SDK_ID_BPM = "N7jbSW#j7YrFd~sE"
    const val SDK_ID_THERMO = "1XYXF1bm8ZyEM81k"
    const val SDK_ID_WEIGHT = "1??8xz5Zpt6cbw5z"

    const val KEY_LAST_DEVICE = "LAST_DEVICE"

    const val TYPE_TEMPERATURE = 1
    const val TYPE_HEARTRATE = 2
    const val TYPE_BLOOD_PRESSURE = 3
    const val TYPE_BMI = 4
    const val TYPE_HEIGHT = 5
    const val TYPE_WEIGHT = 6
    const val TYPE_WAIST = 7
    const val TYPE_BLOOD_OXYGEN = 8
    const val TYPE_STETHOSCOPE = 9
    const val TYPE_ECG = 10
    const val TYPE_STEP = 11
    const val TYPE_SLEEP = 12
    const val TYPE_RESPIRATION = 13
    const val BASE_URL_DEVICE = "http://192.168.100.154:8000/api/"
    const val URL_HISTORY_MEASUREMENT = "measurement/history"
    const val BASE_URL_WEB= "https://app.cexup.com/"
    const val SKIN_WHITE =0x00
    const val SKIN_WHITE_YELLOW= 0x01
    const val SKIN_YELLOW = 0x02
    const val SKIN_BROWN1 =0x03
    const val SKIN_BROWN2 =0x04
    const val SKIN_BLACK = 0x05
    const val SKIN_OTHER = 0x07
    object WEARING_POSITION{
        const val LEFT = 0x00
        const val RIGHT = 0x01

    }
    object HISTORY{
        const val RESP_TEMP_SPO2 = 0x0509
        const val STEP = 0x0502
        const val SLEEP = 0x0504
        const val HR = 0x0506
        const val BPM = 0x0508
    }
    //0x00:steps 0x01:calories 0x02:distance 0x03:sleep
    object SETTING_GOAL{
        const val SLEEP=0x03
        const val STEP = 0x00
        const val CALORIES = 0x02
        const val DISTANCE = 0x02
        const val DEFAULT = 0x00
    }
}
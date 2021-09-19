package com.trian.common.utils.route

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

object  Routes{
    const val SPLASH = "SPLASH"
    const val ONBOARD = "ONBOARD"
    const val DETAIL_HEALTH = "DETAIL_HEALTH"
    const val LOGIN = "LOGIN"
    const val REGISTER = "REGISTER"
    const val SHEET_SERVICE = "SHEET_SERVICE"
    const val DASHBOARD = "DASHBOARD"
    const val DETAIL_HOSPITAL = "DETAIL_HOSPITAL"
    const val MOBILE_NURSE ="MOBILE_NURSE"
    const val DETAIL_DOCTOR = "DETAIL_DOCTOR"
    const val FORGET_PASSWORD ="FORGET_PASSWORD"
    const val SUCCESS_FORGET = "SUCCESS_FORGET"
    object NESTED_DASHBOARD{
        const val HOME = "HOME"
        const val RESERVATION = "RESERVATION"
        const val CALL_DOCTOR = "CALL_DOCTOR"
        const val ACCOUNT = "ACCOUNT"
    }

    object SMARTWATCH_ROUTE{
        const val MAIN = "SMARTWATCH_MAIN"
        const val DETAIL_BPM = "DETAIL_BPM"
        const val DETAIL_SPO2 = "DETAIL_SPO2"
        const val DETAIL_RESPIRATION = "DETAIL_RESPIRATION"
        const val DETAIL_TEMPERATURE = "DETAIL_TEMPERATURE"
        const val DETAIL_HEART_RATE = "DETAIL_HEART_RATE"
        const val DETAIL_SLEEP = "DETAIIL_SLEEP"
        const val DETAIL_ECG = "DETAIL_ECG"
    }
}
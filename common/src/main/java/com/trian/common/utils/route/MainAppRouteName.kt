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
    const val DETAIL_ORDER = "DETAIL_ORDER"
    const val PRIVACY_POLICY = "PRIVACY_POLICY"
    const val KUESIONER = "KUESIONER"
    const val SHEET_CANCELORDER = "SHEET_CANCELORDER"
    const val SHEET_FORMORDER = "SHEET_FORMORDER"
    const val SHEET_DETAILHOSPITAL ="SHEET_DETAILHOSPITAL"
    const val SHEET_PRIVACYPOLICY = "SHEET_PRIVACYPOLICY"
    object NESTED_DASHBOARD{
        const val HOME = "HOME"
        const val LIST_HOSPITAL = "LIST_HOSPITAL"
        const val LIST_ORDER = "LIST_ORDER"
        const val ACCOUNT = "ACCOUNT"
    }

    object SMARTWATCH_ROUTE{
        const val MAIN = "SMARTWATCH_MAIN"
        const val DETAIL_BLOOD_PRESSURE = "DETAIL_BPM"
        const val DETAIL_BLOOD_OXYGEN = "DETAIL_SPO2"
        const val DETAIL_RESPIRATION = "DETAIL_RESPIRATION"
        const val DETAIL_TEMPERATURE = "DETAIL_TEMPERATURE"
        const val DETAIL_HEART_RATE = "DETAIL_HEART_RATE"
        const val DETAIL_SLEEP = "DETAIL_SLEEP"
        const val DETAIL_ECG = "DETAIL_ECG"
        const val BOTTOM_SHEET_DEVICES = "BOTTOM_SHEET_DEVICES"
        const val BOTTOM_SHEET_PERMISSION = "BOTTOM_SHEET_PERMISSION"
        const val SETTING_SMARTWATCH ="SETTING_SMARTWATCH"
        const val BOTTOMSHEET_HEALTHMONITORING = "BOTTOMSHEET_HEALTHMONITORING"
        const val BOTTOMSHEET_DISTANCE ="BOTTOMSHEET_DISTANCE"
        const val BOTTOMSHEET_TEMPERATURE = "BOTTOMSHEET_TEMPERATURE"
    }
}
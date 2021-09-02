package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Doctor(
    val title: String,
    val slug: String,
    val description: String,
    val offlineSchedule: Any? = null,
    val onlineSchedule: OnlineSchedule,
    val speciality: String,
    val hospital: String,
    val hospitalList: List<HospitalList>,
    val thumbOriginal: String,
    val thumb: String
)

data class HospitalList (
    val id: Long,
    val name: String,
    val offlinePrice: String,
    val onlinePrice: String
)

data class OnlineSchedule (
    val monday: String,
    val tuesday: String,
    val wednesday: String
)

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
    val offlineSchedule: Schedule? = null,
    val onlineSchedule: Schedule? = null,
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

data class Schedule (
    val sunday:String? = null,
    val monday: String? = null,
    val tuesday: String? = null,
    val wednesday: String? = null,
    val thursday:String? = null,
    val friday:String? = null,
    val saturday:String? = null,
)

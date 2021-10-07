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
    val offline_schedule: Schedule? = null,
    val online_schedule: Schedule? = null,
    val speciality: String,
    val hospital: String,
    val hospital_list: List<HospitalList>,
    val thumb_original: String,
    val thumb: String
)

data class HospitalList (
    val id: Long,
    val name: String,
    val offline_price: String,
    val online_price: String
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

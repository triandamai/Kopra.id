package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Speciality(        val title: String,
                              val slug: String,
                              val description: String,
                              val offlineSchedule: Any? = null,
                              val onlineSchedule: Schedule,
                              val speciality: String,
                              val hospital: String,
                              val hospitalList: List<HospitalList>,
                              val thumbOriginal: String,
                              val thumb: String
)


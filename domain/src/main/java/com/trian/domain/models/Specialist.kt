package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Specialist(val title: String,
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


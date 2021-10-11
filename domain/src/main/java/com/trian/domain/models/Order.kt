package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Order(val deleted_schedule: Boolean,
                  val transaction_id: String,
                  val hospital: String,
                  val doctor_hospital_id: Long,
                  val address: String,
                  val doctor: String,
                  val doctor_slug: String,
                  val speciality: String,
                  val patient: String,
                  val patient_id: Long,
                  val note: String,
                  val doctor_note: String,
                  val prescription: String,
                  val provisional: String,
                  val date: String,
                  val estimate: String,
                  val type: String,
                  val price: String,
                  val request_reschedule_patient: Boolean,
                  val request_reschedule_doctor: Boolean,
                  val status_order: Long,
                  val paid: Boolean,
                  val refund: Boolean,
                  val bank_name: String,
                  val account_number: String,
                  val account_name: String,
                  val start: String,
                  val join: Any? = null,
                  val payment_token: String,
                  val allowed: Boolean,
                  val request_access: Boolean,
                 val payment_url:String,
                  val thumb: String
                  )

package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Order( val deletedSchedule: Boolean,
                  val transactionID: String,
                  val hospital: String,
                  val doctorHospitalID: Long,
                  val address: String,
                  val doctor: String,
                  val doctorSlug: String,
                  val speciality: String,
                  val patient: String,
                  val patientID: Long,
                  val note: String,
                  val doctorNote: String,
                  val prescription: String,
                  val provisional: String,
                  val date: String,
                  val estimate: String,
                  val type: String,
                  val price: String,
                  val requestReschedulePatient: Boolean,
                  val requestRescheduleDoctor: Boolean,
                  val statusOrder: Long,
                  val paid: Boolean,
                  val refund: Boolean,
                  val bankName: String,
                  val accountNumber: String,
                  val accountName: String,
                  val start: String,
                  val join: Any? = null,
                  val paymentToken: String,
                  val allowed: Boolean,
                  val requestAccess: Boolean,
                  val thumb: String)

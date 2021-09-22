package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class DetailOrder(
    val deletedSchedule:Any?,
    val transactionId:String,
    val hospital: String,
    val doctorHospitalId:Int,
    val address:String,
    val doctor:String,
    val doctorSlug:String,
    val speciality:String,
    val patient:String,
    val patientId:Int,
    val note:String,
    val doctorNote:String,
    val prescription:String,
    val provisional:String,
    val date:String,
    val estimate:String,
    val type: String,
    val price:String,
    val requestReschedulePatient:Any,
    val requestRescheduleDoctor:Any,
    val statusOrder:Int,
    val paid:Boolean,
    val refund:Boolean,
    val bankName:String,
    val accountNumber:String,
    val accountName:String,
    val start:Boolean,
    val join:Boolean,
    val paymentToken:String,
    val allowed: Boolean,
    val requestAccess:Boolean,
    val paymentUrl:String,
    val thumb:String,
)

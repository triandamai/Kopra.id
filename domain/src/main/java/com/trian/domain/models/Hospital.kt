package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Hospital(
    val id:Int,
    val slug:String,
    val description:String?="",
    val name:String,
    val address:String?="",
    val others:String,
    val thumb_original:String?="",
    val thumb:String?="",
    )

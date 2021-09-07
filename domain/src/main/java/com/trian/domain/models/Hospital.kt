package com.trian.domain.models

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

data class Hospital(
    val id:Int,
    val slug:String,
    val description:String,
    val name:String,
    val address:String,
    val others:String,
    val thumbOriginal:String,
    val thumb:String,
    )

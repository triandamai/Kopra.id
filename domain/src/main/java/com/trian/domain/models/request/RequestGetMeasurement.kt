package com.trian.domain.models.request

import com.trian.domain.entities.Measurement
import com.trian.domain.models.Devices

data class RequestGetMeasurement(
    val id: String,

    //not blank only for String

    val member_id: String,

    val nurse_id: String,

    val type: Int,

    val device: String,

    val value: String,

    val method: String,
    val created_at: Long,
    val updated_at: Long
)

fun RequestGetMeasurement.toMeasurement():Measurement=
    Measurement(
        member_id = member_id,
        nurse_id = nurse_id,
        type = type,
        device_id = device,
        value = value,
        is_upload = true,
        method = method,
        created_at = created_at,
        updated_at = updated_at
    )
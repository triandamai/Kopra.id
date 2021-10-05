package com.trian.domain.models.request

import com.trian.domain.entities.Measurement

data class RequestPostMeasurement(
    val device_id: String,

    val member_id: String,

    val nurse_id: String,

    val type: Int,

    val value: String,

    val method: String,

    val created_at: Long
)

fun Measurement.toRequest():RequestPostMeasurement=
    RequestPostMeasurement(
       device_id= device_id,
        member_id=member_id,
        nurse_id =nurse_id,
        type=type,
        value=value,
        method=method,
        created_at =created_at
    )

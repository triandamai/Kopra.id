package com.trian.domain.models.request

import com.trian.domain.entities.Measurement
import com.trian.domain.models.Devices

data class RequestGetMeasurement(
    val id: String,

    //not blank only for String

    val member_id: String,

    val nurse_id: String,

    val type: Int,

    val device: DeviceResponse,

    val value: String,

    val method: String,
    val created_at: Long,
    val updated_at: Long
)
data class DeviceResponse(
    val id: String,
    val deviceName: String,
    val deviceMac: String,
    val deviceType: String,
    val deviceHolder: String,
    val createdAt: Long,
    val updatedAt: Long
)

fun RequestGetMeasurement.toMeasurement():Measurement=
    Measurement(
        member_id = member_id,
        nurse_id = nurse_id,
        type = type,
        device_id = device.deviceMac,
        value = value,
        is_upload = true,
        method = method,
        created_at = created_at,
        updated_at = updated_at
    )
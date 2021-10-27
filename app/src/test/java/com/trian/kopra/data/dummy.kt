package com.trian.kopra.data

import com.trian.common.utils.sdk.SDKConstant
import com.trian.domain.entities.Measurement

val idMember = "ini_user_code_unique"
val measurements = listOf(
    Measurement(
        id= 1,
        member_id=idMember,
        nurse_id="",
        device_id="ini device id",
        type=SDKConstant.TYPE_TEMPERATURE,
        value="34",
        created_at=1632889813000,
        end_at=1632889813000,
        updated_at=1632889813000,
        is_upload=false,
    ),
    Measurement(
        id= 2,
        member_id=idMember,
        nurse_id="",
        device_id="ini device id",
        type=SDKConstant.TYPE_RESPIRATION,
        value="21",
        created_at=1632889813000,
        end_at=1632889813000,
        updated_at=1632889813000,
        is_upload=false,
    ),
    Measurement(
        id= 3,
        member_id=idMember,
        nurse_id="",
        device_id="ini device id",
        type=SDKConstant.TYPE_BLOOD_OXYGEN,
        value="90",
        created_at=1632889813000,
        end_at=1632889813000,
        updated_at=1632889813000,
        is_upload=false,
    ),
    Measurement(
        id= 4,
        member_id=idMember,
        nurse_id="",
        device_id="ini device id",
        type=SDKConstant.TYPE_BLOOD_PRESSURE,
        value="122/78",
        created_at=1632889813000,
        end_at=1632889813000,
        updated_at=1632889813000,
        is_upload=false,
    ),
)
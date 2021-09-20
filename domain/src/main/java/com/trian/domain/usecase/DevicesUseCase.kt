package com.trian.domain.usecase

import com.trian.domain.models.Devices

data class DevicesUseCase(
    var scanning:Boolean=false,
    var devices:MutableList<Devices> = mutableListOf(),
    var error:String=""
)
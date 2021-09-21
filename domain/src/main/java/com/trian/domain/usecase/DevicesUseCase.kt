package com.trian.domain.usecase

import com.trian.domain.models.Devices

data class DevicesUseCase(
    var scanning:Boolean=false,
    var devices:List<Devices> = mutableListOf(),
    var error:String=""
)
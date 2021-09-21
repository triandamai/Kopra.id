package com.trian.domain.usecase

import com.trian.domain.entities.Measurement

data class MeasurementUseCase (
    var loading:Boolean=false,
    var measurements:List<Measurement> = listOf()
    )
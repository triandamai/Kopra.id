package com.trian.domain.models

data class Service(
    var title:String,
    var icon:Int,
    var type:ServiceType = ServiceType.HEALTH_TRACKER
)

enum class ServiceType {
    TELECONSULTATION,
    HEALTH_TRACKER,
    COVID_MONITORING,
    SHOP,
    RESERVATION,
    MEDICAL_CHECKUP,
    MEDICINE,
    MEDICAL_RECORD,
    MOBILE_NURSE
}

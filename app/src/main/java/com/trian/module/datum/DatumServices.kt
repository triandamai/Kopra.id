package com.trian.module.datum

import com.trian.domain.models.Service
import com.trian.component.R
import com.trian.domain.models.ServiceType

/**
 * Datum Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */

 val listServices = listOf<Service>(
    Service("Teleconsultation", R.drawable.services_teleconsultation,ServiceType.TELECONSULTATION),
    Service("Health Tracker", R.drawable.services_health_tracker,ServiceType.HEALTH_TRACKER),
    Service("Covid Monitoring", R.drawable.services_covid_monitoring,ServiceType.COVID_MONITORING),
    Service("Shop", R.drawable.services_shop,ServiceType.SHOP),
    Service("Reservation", R.drawable.services_reservation,ServiceType.RESERVATION),
    Service("Home Visit", R.drawable.services_mobile_nurse,ServiceType.MOBILE_NURSE),
    Service("Medical Checkup", R.drawable.services_medical_checkup,ServiceType.MEDICAL_CHECKUP),
    Service("Medicine", R.drawable.services_medicine,ServiceType.MEDICINE),
    Service("Medical record", R.drawable.services_medical_record,ServiceType.MEDICAL_RECORD),
)
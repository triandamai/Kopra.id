package com.cexup_sdk.services

import com.cexup_sdk.storage.room.entity.Measurement
import com.cexup_sdk.storage.room.entity.Nurse
import com.cexup_sdk.storage.room.entity.Patient

interface Repository {
    suspend fun login(result:(nurse:Nurse)->Unit)
    suspend fun getListUsers(result:(patient:Patient)->Unit)
    suspend fun saveMeasurement(measurement: Measurement,result:(success:Boolean,message:String)->Unit)
    suspend fun saveMeasurement(measurements: List<Measurement>,result:(success:Boolean,message:String)->Unit)
}
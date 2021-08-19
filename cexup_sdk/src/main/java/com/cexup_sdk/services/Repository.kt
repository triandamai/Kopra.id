package com.cexup_sdk.services

import com.cexup_sdk.storage.room.entity.Measurement
import com.cexup_sdk.storage.room.entity.Nurse
import com.cexup_sdk.storage.room.entity.Patient

interface Repository {
    suspend fun login(username:String,password:String,result:(success:Boolean,nurse:Nurse)->Unit)
    suspend fun getListUsers(result:(patient:List<Patient>?)->Unit)
    suspend fun saveMeasurement(measurement: Measurement, type:String,result:(success:Boolean,message:String)->Unit)
    suspend fun saveMeasurement(measurements: List<Measurement>, type:String,result:(success:Boolean,message:String)->Unit)
}
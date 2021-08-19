package com.cexup_sdk.services

import com.cexup_sdk.storage.persistence.Persistence
import com.cexup_sdk.storage.room.DataStorage
import com.cexup_sdk.storage.room.entity.Measurement
import com.cexup_sdk.storage.room.entity.Nurse
import com.cexup_sdk.storage.room.entity.Patient

class CexupRepository(
    private val dataStorage: DataStorage,
    private val persistence: Persistence
    ):Repository {
    private val cexupApi: Api = Api(persistence)
    override suspend fun login(username:String,password:String,result: (success:Boolean,nurse: Nurse) -> Unit) {
         cexupApi.loginAsNurse(username,password) {
             result(it.success,it.data)
         }
    }

    override suspend fun getListUsers(result: (patient: List<Patient>?) -> Unit) {
        //get from api
        cexupApi.getListUsers(persistence.currentToken()){
            dataStorage.patientDao().patientTransaction(it.data)
        }
        //check local
        result(dataStorage.patientDao().all())
    }

    override suspend fun saveMeasurement(
        measurement: Measurement,
        type:String,
        result: (success: Boolean, message: String) -> Unit
    ) {
        cexupApi.sendMeasurement(measurement = measurement,type = type){

            measurement.is_upload = it.success
            dataStorage.measurementDao().insert(measurement)
            result(it.success,it.message)
        }
    }

    override suspend fun saveMeasurement(
        measurements: List<Measurement>,
        type: String,
        result: (success: Boolean, message: String) -> Unit
    ) {
        cexupApi.sendMeasurement(measurements = measurements,type = type){
            dataStorage.measurementDao().measureTransaction(measurements,it.success)
            result(it.success,it.message)
        }
    }
}
package com.cexup_sdk.services

import com.cexup_sdk.storage.room.entity.Measurement
import com.cexup_sdk.storage.room.entity.Nurse
import com.cexup_sdk.storage.room.entity.Patient

class CexupRepository(private val cexupApi: Api):Repository {

    override suspend fun login(result: (nurse: Nurse) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun getListUsers(result: (patient: Patient) -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun saveMeasurement(
        measurement: Measurement,
        result: (success: Boolean, message: String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun saveMeasurement(
        measurements: List<Measurement>,
        result: (success: Boolean, message: String) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
package com.cexup_sdk.services

import com.cexup_sdk.storage.room.entity.Patient

interface Repository {
    suspend fun login()
    suspend fun getListUsers():List<Patient>
}
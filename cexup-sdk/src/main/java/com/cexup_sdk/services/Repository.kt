package com.cexup_sdk.services

import library.storage.room.entity.Patient

interface Repository {
    suspend fun login()
    suspend fun getListUsers():List<Patient>
}
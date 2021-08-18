package com.cexup_sdk.services

import com.cexup_sdk.storage.room.entity.Patient

class CexupRepository(private val cexupApi: Api):Repository {
    override suspend fun login() {

    }

    override suspend fun getListUsers(): List<Patient> {
        //get user in db and api
        return listOf()
    }
}
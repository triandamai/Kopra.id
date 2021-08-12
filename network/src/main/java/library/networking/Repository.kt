package library.networking

import library.data.storage.room.entity.Patient

class Repository {
    suspend fun getAllUsers():List<Patient?>{
        val users= mutableListOf<Patient>()

        return users
    }
}
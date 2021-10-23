package com.trian.data.repository

import com.trian.data.remote.FirebaseSource

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */

class UserRepositoryImpl(
    private val firebaseSource: FirebaseSource,
):UserRepository {
}
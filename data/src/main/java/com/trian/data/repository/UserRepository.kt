package com.trian.data.repository

import com.trian.common.utils.network.DataOrException
import com.trian.domain.models.User

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */

interface UserRepository {
    suspend fun getUserById(id:String): DataOrException<User, Exception>
}
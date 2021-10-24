package com.trian.data.repository

import android.app.Activity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.PhoneAuthProvider
import com.trian.common.utils.network.DataOrException
import com.trian.domain.models.User

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */

interface UserRepository {
    suspend fun sendOTP(otp:String,activity: Activity,callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks)
    suspend fun createUser(user:User)
    suspend fun getUserById(id:String): DataOrException<User, Exception>
}
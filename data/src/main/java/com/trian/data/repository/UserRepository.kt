package com.trian.data.repository

import android.app.Activity
import android.graphics.Bitmap
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.trian.domain.models.network.CurrentUser
import com.trian.domain.models.network.DataOrException
import com.trian.domain.models.User
import kotlinx.coroutines.flow.Flow

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */

interface UserRepository {
    fun currentUser():Flow<CurrentUser>
    fun createUser(user:User)
    fun updateUser(user: User,onComplete: (success: Boolean, url: String) -> Unit)
    fun uploadImageProfile(bitmap: Bitmap,onComplete:(success:Boolean,url:String)->Unit)
    suspend fun sendOTP(otp:String,activity: Activity,callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks)
    suspend fun signIn(credential: PhoneAuthCredential,finish:(success:Boolean,message:String)->Unit)
    suspend fun getUserById(id:String): DataOrException<User, Exception>
}
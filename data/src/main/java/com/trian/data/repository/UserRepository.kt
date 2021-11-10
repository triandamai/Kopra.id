package com.trian.data.repository

import android.app.Activity
import android.graphics.Bitmap
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.trian.domain.models.Store
import com.trian.domain.models.network.CurrentUser
import com.trian.domain.models.network.DataOrException
import com.trian.domain.models.User
import com.trian.domain.models.network.GetStatus
import kotlinx.coroutines.flow.Flow

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */

interface UserRepository {
    fun firebaseUser():FirebaseUser?
    fun getCurrentUser(onResult:(hasUser:Boolean,user:User)->Unit)

    fun createUser(user:User,onComplete: (success: Boolean, url: String) -> Unit)
    fun setLocalUser(user:User)
    fun updateUser(user: User,onComplete: (success: Boolean, url: String) -> Unit)
    fun uploadImageProfile(bitmap: Bitmap,onComplete:(success:Boolean,url:String)->Unit)

    fun sendOTP(otp:String,activity: Activity,callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks)
    fun signIn(credential: PhoneAuthCredential,finish:(success:Boolean,shouldUpdate:Boolean,user:User,message:String)->Unit)
    fun signOut()

    suspend fun  syncUser()

    suspend fun getUserById(id:String): GetStatus<User>
    suspend fun getUserByUid(id:String): User?

}
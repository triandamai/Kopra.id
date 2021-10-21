package com.trian.data.remote

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */

object DataRemote{
    private const val TAG = ""

    suspend fun getProfile():Any{
        val db = FirebaseFirestore.getInstance()
        return try {
            db.collection("")
                .document("")
                .get().await()
        }catch (e:Exception){

        }
    }

    suspend fun signInPhoneNumber(
        phoneNumber:String,
        activity: Activity,
        callback:PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ){
        val auth = FirebaseAuth.getInstance()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setActivity(activity)
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}
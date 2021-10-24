package com.trian.data.repository

import android.app.Activity
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.trian.common.utils.network.DataOrException
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.User
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */


class UserRepositoryImpl(
    private val  source: FirestoreSource
):UserRepository {
    override suspend fun sendOTP(
        otp: String,
        activity: Activity,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {
        val options = PhoneAuthOptions.newBuilder(source.firebaseAuth)
            .setPhoneNumber(otp)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override suspend fun createUser(user: User) {
        source.userCollection().document("set").set(user)
    }
    override suspend fun getUserById(id:String): DataOrException<User, Exception> {
        val dataOrException = DataOrException<User, Exception>()
        try {
          dataOrException.data = source.userCollection().document(id).get().await().toObject(User::class.java)
        }catch (e:Exception){
            dataOrException.e = e
        }
        return dataOrException
    }
}
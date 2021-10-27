package com.trian.data.repository

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.trian.common.utils.network.DataOrException
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.LevelUser
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

    override suspend fun signIn(credential: PhoneAuthCredential,finish:(success:Boolean,message:String)->Unit) {
        source.firebaseAuth.signInWithCredential(credential)
            .addOnCanceledListener { finish(false,"Canceled") }
            .addOnCompleteListener {
                task->
                if (task.isSuccessful){
                   val user = task.result?.user

                    createUser(
                        User(
                        user!!.uid,
                        user!!.phoneNumber.toString(),
                            "kosong",
                            "kosong",
                            0,
                            0,
                            LevelUser.FARMER,
                            getTodayTimeStamp(),
                            getTodayTimeStamp()
                        )
                    )
                    finish(true,"Success")
                }else{
                    finish(false,"No complete")
                }

            }
            .addOnFailureListener { finish(false,it.message!!) }
    }

    override  fun createUser(user: User) {
        source.userCollection().document(user.uid).set(user)
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
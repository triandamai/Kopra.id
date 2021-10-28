package com.trian.data.repository

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.trian.common.utils.utils.CollectionUtils
import com.trian.domain.models.network.CurrentUser
import com.trian.domain.models.network.DataOrException
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.LevelUser
import com.trian.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */


class UserRepositoryImpl(
    private val  source: FirestoreSource
):UserRepository {
    override fun currentUser(): Flow<CurrentUser> = flow {

        source.firebaseAuth.currentUser?.let {
            Log.e("REU",it.uid)
            try {
                val user = source.userCollection().document(it.uid).get().await().toObject(User::class.java)
                user?.let { currentUser ->
                    Log.e("REC",currentUser.toString())
                    if(
                        currentUser.fullName == CollectionUtils.NO_DATA_DEFAULT ||
                        currentUser.address == CollectionUtils.NO_DATA_DEFAULT
                    ) {
                        emit(CurrentUser.UserNotComplete(currentUser))
                    }else{
                        emit(CurrentUser.HasUser(user = currentUser))
                    }

                }?:run {
                    Log.e("RER",it.uid)
                    emit(CurrentUser.UserNotComplete(User()))
                }
            }catch (e:Exception){
                Log.e("RER",e.message.toString())
                emit(CurrentUser.UserNotComplete(User()))
            }
        }?: kotlin.run {
            emit(CurrentUser.NoUser())
        }
    }

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
                            CollectionUtils.NO_DATA_DEFAULT,
                            CollectionUtils.NO_DATA_DEFAULT,
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


    override fun uploadImageProfile(
        bitmap: Bitmap,
        uid:String,
        onComplete: (success: Boolean, url: String) -> Unit
    ) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = source.storageUser().child(uid).putBytes(data)

        uploadTask.addOnSuccessListener {

        }.addOnFailureListener {

        }

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
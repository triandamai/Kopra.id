package com.trian.data.repository

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.trian.common.utils.utils.CollectionUtils
import com.trian.domain.models.network.CurrentUser
import com.trian.domain.models.network.DataOrException
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.data.local.Persistence
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.*
import com.trian.domain.models.network.GetStatus
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
    private val  source: FirestoreSource,
    private val persistence: Persistence
):UserRepository {
    override  fun sendOTP(
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

    override fun signIn(credential: PhoneAuthCredential,finish:(success:Boolean,shouldUpdate:Boolean,newUser:User,message:String)->Unit) {
        source.firebaseAuth.signInWithCredential(credential)
            .addOnCanceledListener {
                finish(false,false,User(),"Canceled")
            }
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    val user = task.result?.user
                    user?.let {
                        firebaseUser->
                        source.userCollection().document(firebaseUser.uid)
                            .get()
                            .addOnSuccessListener {
                                document->
                                if(document.exists()){
                                    val user = document.toObject(User::class.java)
                                        user?.let {
                                            finish(true,it.checkShouldUpdateProfile(),it,"")
                                        }?:run{
                                            finish(true,true, User(),"")
                                        }
                                }else{
                                    finish(true,true,User(),"")
                                }

                            }.addOnFailureListener {
                                finish(true,true,User(),"")
                            }
                    }?:run{
                        finish(true,true,User(),task.toString())
                    }

                }else{
                    finish(false,false,User(),task.toString())
                }
            }
            .addOnFailureListener {
                finish(false,false,User(),it.message!!)
            }
    }

    override fun signOut() {
        persistence.dropUser()
        source.firebaseAuth.signOut()
    }
    override fun firebaseUser(): FirebaseUser? {
      return  source.firebaseAuth.currentUser
    }

    override fun getCurrentUser(onResult: (hasUser: Boolean,user:User) -> Unit) {
        val user = persistence.getUser()

        user?.let {user->
            source.firebaseAuth.currentUser?.let {firebaseuser->
                user.apply {
                    uid = firebaseuser.uid
                }
                onResult(true,user)
            }?: run {
                onResult(false,User())
            }

        }?:run{onResult(false, User())}
    }



    override fun createUser(user: User,onComplete: (success: Boolean, message: String) -> Unit) {
        source.userCollection()
            .document(user.uid)
            .set(user)
            .addOnCompleteListener {
                onComplete(true,"")
            }
            .addOnFailureListener {
                onComplete(false,"")
            }
    }

    override fun setLocalUser(user: User) {
        persistence.setUser(user)
    }
    override fun updateUser(user: User,onComplete: (success: Boolean, url: String) -> Unit) {
        source.firebaseAuth.currentUser?.let {
            firebaseUser->
            user.apply {
                uid = firebaseUser.uid
                phoneNumber = firebaseUser.phoneNumber.toString()
            }
            source.userCollection().document(firebaseUser.uid)
                .set(user.toUpdatedData(), SetOptions.merge())
                .addOnCompleteListener {
                    task->
                    if(task.isSuccessful){
                        persistence.setUser(user)
                        onComplete(true,"success")
                    }else{
                        onComplete(false,task.result.toString())
                    }
                }.addOnFailureListener {
                    onComplete(false,it.message!!)
                }
        }?:onComplete(false,"Not legged in")
    }


    override fun uploadImageProfile(
        bitmap: Bitmap,
        onComplete: (success: Boolean, url: String) -> Unit
    ) {
        val user = source.firebaseAuth.currentUser
        user?.let {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val storageReference = source
                .storageUser()
                .child(it.uid)

            storageReference.putBytes(data)
                .continueWith {
                    task->
                    if(!task.isSuccessful){
                        task.exception?.let {
                            throw  it
                        }
                    }
                    storageReference.downloadUrl

                }
                .addOnCompleteListener {
                    task->
                    if(task.isSuccessful){
                        val url = task.result
                        url!!.addOnSuccessListener {
                        uri->
                            onComplete(true,uri.toString())
                        }.addOnFailureListener {e->
                            onComplete(false,e.message!!)
                        }
                    }else{
                        onComplete(false,"task not success")
                    }

                }.addOnFailureListener {
                    onComplete(false,"")
            }

        }?:onComplete(false,"")
    }


    override suspend fun syncUser() {
        source.firebaseAuth.currentUser?.let {
            val user = source.userCollection().document(it.uid).get().await().toObject(User::class.java)
            user?.let {
                user->
                setLocalUser(user)
            }
        }
    }

    override suspend fun getUserById(id:String): GetStatus<User> {
       return try {
           val result = source.userCollection().document(id)
               .get().await().toObject(User::class.java)
           GetStatus.HasData(result)
       }catch (e:Exception){
           GetStatus.NoData(e.message!!)
       }
    }

    override suspend fun getUserByUid(id: String): User? {
        return try {
               source.userCollection().document(id)
                .get().await().toObject(User::class.java)

        }catch (e:Exception){
           null
        }
    }


}
package com.trian.data.viewmodel

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.trian.data.repository.UserRepository
import com.trian.domain.models.LevelUser
import com.trian.domain.models.User
import com.trian.domain.models.network.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 22/10/2021
 */
@HiltViewModel
class MainViewModel @Inject constructor(
private val userRepository: UserRepository
) :ViewModel() {

    val storedVerificationId : MutableState<String>  = mutableStateOf("")
    val resendToken : MutableState<PhoneAuthProvider.ForceResendingToken?>  = mutableStateOf(null)

     val currentUser:Flow<CurrentUser> = userRepository.currentUser()

    fun sendOTP(otp:String,activity: Activity,finish:(success:Boolean,message:String)->Unit)=viewModelScope.launch {
        userRepository.sendOTP(otp,activity,object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    Log.e("onVerificationCompleted",credential.toString())
                    signIn(credential,finish)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("onVerificationFailed",e.message.toString())

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.e("onVerificationFailed",e.message.toString())

                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Log.e("onVerificationFailed",e.message.toString())
                }
            }

            override fun onCodeSent(verifivationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verifivationId, token)
                Log.e("onCodeSent",verifivationId)
                Log.e("onCodeSent2",token.toString())

                storedVerificationId.value = verifivationId
                resendToken.value = token
            }
        })
    }

    fun resendToken(code:String,finish: (success: Boolean, message: String) -> Unit){
        val credential = PhoneAuthProvider.getCredential(storedVerificationId.value,code)

        signIn(credential,finish)
    }

    fun signIn(credential:PhoneAuthCredential,finish: (success: Boolean, message: String) -> Unit)=viewModelScope.launch {
        userRepository.signIn(credential,finish)
    }


}
package com.trian.data.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.trian.data.repository.UserRepository
import com.trian.domain.models.LevelUser
import com.trian.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun sendOTP(otp:String,activity: Activity)=viewModelScope.launch {
        userRepository.sendOTP(otp,activity,object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                TODO("Not yet implemented")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                TODO("Not yet implemented")
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
            }
        })
    }

    fun createUser()=viewModelScope.launch {
        userRepository.createUser(User(
            "iniUID",
            8997870,
            "trian",
            "damai",
            678,
            6789,
            LevelUser.TENANT,
            0,
            0))
    }
}
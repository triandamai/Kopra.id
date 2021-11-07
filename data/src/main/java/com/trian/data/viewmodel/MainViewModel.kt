package com.trian.data.viewmodel

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.data.remote.FirestoreSource
import com.trian.data.repository.StoreRepository
import com.trian.data.repository.TransactionRepository
import com.trian.data.repository.UserRepository
import com.trian.domain.models.*
import com.trian.domain.models.network.GetStatus
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
    private val userRepository: UserRepository,
    private val transactionRepository: TransactionRepository,
    private val storeRepository: StoreRepository,
    private val source: FirestoreSource,

) :ViewModel() {

    //userProfile
    val userFullName: MutableState<String> = mutableStateOf("")
    val userUsername: MutableState<String> = mutableStateOf("")
    val userAddress: MutableState<String> = mutableStateOf("")
    val userProfileImageUrl: MutableState<String> = mutableStateOf("")
    val userBornDate: MutableState<String> = mutableStateOf("")
    val userLevel:MutableState<LevelUser> = mutableStateOf(LevelUser.UNKNOWN)

    //
    val storedVerificationId: MutableState<String> = mutableStateOf("")
    val resendToken: MutableState<PhoneAuthProvider.ForceResendingToken?> = mutableStateOf(null)

    val myStore: MutableState<GetStatus<Store>> = mutableStateOf(GetStatus.NoData(""))
    val detailStore: MutableState<GetStatus<Store>> = mutableStateOf(GetStatus.NoData(""))
    val productList: MutableState<GetStatus<List<Product>>> = mutableStateOf(GetStatus.NoData(""))
    val currentUser:MutableState<User?> = mutableStateOf(null)

    //store
    var storeName :MutableState<String> = mutableStateOf("")
    var storeAddress :MutableState<String> = mutableStateOf("")
    var storeDescription :MutableState<String> = mutableStateOf("")
    var storePhoneNumber :MutableState<String> = mutableStateOf("")
    val storeProfileImageUrl: MutableState<String> = mutableStateOf("")

    fun sendOTP(
        otp: String,
        activity: Activity,
        finish: (success: Boolean, shouldUpdate: Boolean, message: String) -> Unit
    ) = viewModelScope.launch {
        userRepository.sendOTP(
            otp,
            activity,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                    signIn(credential, finish)
                }

                override fun onVerificationFailed(e: FirebaseException) {

                    if (e is FirebaseAuthInvalidCredentialsException) {
                        // Invalid request

                    } else if (e is FirebaseTooManyRequestsException) {
                        // The SMS quota for the project has been exceeded
                      }
                }

                override fun onCodeSent(
                    verifivationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verifivationId, token)
                    Log.e("onCodeSent", verifivationId)
                    Log.e("onCodeSent2", token.toString())

                    storedVerificationId.value = verifivationId
                    resendToken.value = token
                }
            })
    }

    fun resendToken(
        code: String,
        finish: (success: Boolean, shouldUpdate: Boolean, message: String) -> Unit
    ) {
        try {
            val credential = PhoneAuthProvider.getCredential(storedVerificationId.value, code)
            signIn(credential, finish)
        }catch (e:Exception){
            finish(false,false,e.message!!)
        }

    }

    fun signIn(
        credential: PhoneAuthCredential,
        finish: (success: Boolean, shouldUpdate: Boolean, message: String) -> Unit
    ) = viewModelScope.launch {
        userRepository.signIn(credential) { success, user, message ->
            if (success) {
                user?.let { firebaseUser ->
                    viewModelScope.launch {
                        userRepository.getUserByUid(firebaseUser.uid)?.let {
                            userRepository.setLocalUser(it)
                            finish(true, it.checkShouldUpdateProfile(), message)
                        } ?: run {
                            val user = User(uid = firebaseUser.uid)
                            userRepository.createUser(user = user) {
                                    success, url ->
                            }
                            userRepository.setLocalUser(user)
                            finish(true, true, "")
                        }
                    }
                } ?: finish(false, false, message)
            } else {
                finish(false, false, message)
            }
        }
    }

    fun getCurrentUser(onResult:(hasUser:Boolean,user:User)->Unit){
        userRepository.getCurrentUser(onResult)
    }
    fun uploadImage(bitmap: Bitmap, finish: (success: Boolean, url: String) -> Unit) {
        userRepository.uploadImageProfile(bitmap) { s, u ->
            Log.e("uploadImage", u.toString())
            finish(s, u)
            if (s) {
                userProfileImageUrl.value = u
            }
        }
    }

    fun updateProfile(finish: (success: Boolean, message: String) -> Unit) {
        val user = User()
        user.apply {
            fullName = userFullName.value
            username = userUsername.value
            address = userAddress.value
            ttl = userBornDate.value
            levelUser = LevelUser.FARMER
            profilePicture = userProfileImageUrl.value
            updatedAt = getTodayTimeStamp()
        }
        userRepository.updateUser(user, finish)
    }


    fun signOut(onSignOut: () -> Unit) {
        userRepository.signOut()
        onSignOut()
    }


    fun getDetailMyStore() = viewModelScope.launch {
        currentUser.value?.let {
            detailStore.value = storeRepository.getDetailStore(it.uid)
        }
    }

    fun getDetailStore(id:String) = viewModelScope.launch {
        detailStore.value = storeRepository.getDetailStore(id)
    }

    fun createNewStore(onComplete:(success:Boolean)->Unit){
      val store = Store(
          storeName = storeName.value,
          addressStore = storeAddress.value,
          description = storeDescription.value,
          phoneNumber = storePhoneNumber.value,
          logo = storeProfileImageUrl.value,
          createdAt = getTodayTimeStamp(),
          updatedAt = getTodayTimeStamp()
      )
            storeRepository.createStore(store){
                    success: Boolean, _: String ->
                onComplete(success)
            }


    }


    fun getProductByStoreAsOwner()=viewModelScope.launch {
        productList.value = GetStatus.Loading()
        productList.value=    currentUser.value?.let {
          storeRepository.getListProductByStore(it.uid)
        }?:GetStatus.NoData("")
    }

    fun getProductByStoreAsConsumer(storeId:String)=viewModelScope.launch {
        productList.value = GetStatus.Loading()
        productList.value= storeRepository.getListProductByStore(storeId)

    }



}
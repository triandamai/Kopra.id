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
import com.trian.common.utils.utils.CollectionUtils
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

    val myStore: MutableState<GetStatus<Store>> = mutableStateOf(GetStatus.Loading())
    val detailStore: MutableState<GetStatus<Store>> = mutableStateOf(GetStatus.NoData(""))
    val detailProduct: MutableState<GetStatus<Product>> = mutableStateOf(GetStatus.NoData(""))
    val productList: MutableState<GetStatus<List<Product>>> = mutableStateOf(GetStatus.NoData(""))
    val recomendationListStore: MutableState<GetStatus<List<Store>>> = mutableStateOf(GetStatus.NoData(""))
    val listCollector: MutableState<GetStatus<List<Store>>> = mutableStateOf(GetStatus.NoData(""))
    val listTenant: MutableState<GetStatus<List<Store>>> = mutableStateOf(GetStatus.NoData(""))
    val currentUser:MutableState<User?> = mutableStateOf(null)

    //store
    var storeName :MutableState<String> = mutableStateOf("")
    var storeAddress :MutableState<String> = mutableStateOf("")
    var storeDescription :MutableState<String> = mutableStateOf("")
    var storePhoneNumber :MutableState<String> = mutableStateOf("")
    val storeProfileImageUrl: MutableState<String> = mutableStateOf("")

    //product
    val productImageUrl: MutableState<String> = mutableStateOf("")
    val productFullName: MutableState<String> = mutableStateOf("")
    val productDescription :MutableState<String> = mutableStateOf("")
    val productCategory :MutableState<ProductCategory> = mutableStateOf(ProductCategory.UNKNOWN)
    val productPrice :MutableState<Double> = mutableStateOf(0.0)
    val productUnit :MutableState<UnitProduct> = mutableStateOf(UnitProduct.KG)

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
            Log.e("resendToken",credential.smsCode.toString())
            signIn(credential, finish)
        }catch (e:Exception){
            Log.e("resendToken",e.message.toString())
            finish(false,false,e.message!!)
        }

    }

    fun signIn(
        credential: PhoneAuthCredential,
        finish: (success: Boolean, shouldUpdate: Boolean, message: String) -> Unit
    ) = viewModelScope.launch {
        userRepository.signIn(credential) { success, user, message ->
            Log.e("resendToken","$success - ${user.toString()} - $message")
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
    fun uploadImageProfile(bitmap: Bitmap, finish: (success: Boolean, url: String) -> Unit) {
        userRepository.uploadImageProfile(bitmap) { s, u ->
            finish(s, u)
            if (s) {
                userProfileImageUrl.value = u
            }
        }
    }
    fun uploadImageStore(bitmap: Bitmap, finish: (success: Boolean, url: String) -> Unit) {
        storeRepository.uploadLogo(bitmap) { s, u ->
            finish(s, u)
            if (s) {
                userProfileImageUrl.value = u
            }
        }
    }

    fun uploadImageProduct(bitmap: Bitmap, finish: (success: Boolean, url: String) -> Unit) {
        storeRepository.uploadLogo(bitmap) { s, u ->
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

    fun syncUser() = viewModelScope.launch {
        userRepository.syncUser()
    }

    fun signOut(onSignOut: () -> Unit) {
        userRepository.signOut()
        onSignOut()
    }


    fun getDetailMyStore() = viewModelScope.launch {
        currentUser.value?.let {
            myStore.value = storeRepository.getDetailStore(it.uid)
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
         userRepository.getCurrentUser { hasUser, user ->
            store.apply {
                type = when(user.levelUser){
                    LevelUser.TENANT -> TYPE_STORE.TENANT
                    LevelUser.COLLECTOR -> TYPE_STORE.COLLECTOR
                    LevelUser.FARMER -> TYPE_STORE.UNKNOWN
                    LevelUser.UNKNOWN -> TYPE_STORE.UNKNOWN
                }
            }
        }
            storeRepository.createStore(store){
                    success: Boolean, _: String ->
                onComplete(success)
            }


    }
    
    fun createNewProduct(onComplete: (success: Boolean) -> Unit){
        
        val product = Product()
        userRepository.getCurrentUser { hasUser, user ->
            if(hasUser){
                product.apply {
                    storeUid = user.uid
                    productName = productFullName.value
                    category=productCategory.value
                    price=productPrice.value
                    thumbnail=productImageUrl.value
                    unit=productUnit.value
                    createdAt= getTodayTimeStamp()
                    updatedAt= getTodayTimeStamp()
                }
                storeRepository.createProduct(product = product){
                        success, message ->
                        onComplete(success)
                }
            }else{
                onComplete(false)
            }
        }
        
        
    }
    fun getListRecomendationStore()=viewModelScope.launch {
        recomendationListStore.value = try {
             storeRepository.getListStore()
        }catch (e:Exception){
            GetStatus.NoData(e.message!!)
        }
    }

    fun getListCollectorStore()=viewModelScope.launch {
        listCollector.value = try {
            storeRepository.getListStore()
        }catch (e:Exception){
            GetStatus.NoData(e.message!!)
        }
    }

    fun getListTenantStore()=viewModelScope.launch {
        listTenant.value = try {
            storeRepository.getListStore()
        }catch (e:Exception){
            GetStatus.NoData(e.message!!)
        }
    }


    fun getProductByStoreAsOwner()=viewModelScope.launch {
        productList.value = GetStatus.Loading()
        productList.value=   currentUser.value?.let {
          storeRepository.getListProductByStore(it.uid)
        }?:GetStatus.NoData("")
    }

    fun getProductByStoreAsConsumer(storeId:String)=viewModelScope.launch {
        productList.value = GetStatus.Loading()
        productList.value= storeRepository.getListProductByStore(storeId)
    }

    fun getDetailProduct(productId: String)=viewModelScope.launch {
        detailProduct.value = GetStatus.Loading()
        detailProduct.value = storeRepository.getDetailProduct(productId)
    }

    fun getDetailProductAndStoreByProductId(productId:String)=viewModelScope.launch {
        storeRepository.getDetailProductForCheckOut(productId) { success, product ->
            if(success){
                detailProduct.value = GetStatus.HasData(product)
                viewModelScope.launch {
                    storeRepository.getDetailStoreForCheckOut(product.storeUid){
                            success, store ->
                        if(success){
                            detailStore.value = GetStatus.HasData(store)
                        }else{
                            detailStore.value = GetStatus.NoData("No data Found")
                        }
                    }
                }
            }else{
                detailProduct.value = GetStatus.NoData("Data Not Found")
            }
        }
    }




}
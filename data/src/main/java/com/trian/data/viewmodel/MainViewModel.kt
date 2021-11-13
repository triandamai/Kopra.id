package com.trian.data.viewmodel

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.android.libraries.maps.model.LatLng
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
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

    //data
    val myStore: MutableState<GetStatus<Store>> = mutableStateOf(GetStatus.Loading())
    val detailStore: MutableState<GetStatus<Store>> = mutableStateOf(GetStatus.NoData(""))
    val detailProduct: MutableState<GetStatus<Product>> = mutableStateOf(GetStatus.NoData(""))
    val productList: MutableState<GetStatus<List<Product>>> = mutableStateOf(GetStatus.NoData(""))
    val recomendationListStore: MutableState<GetStatus<List<Store>>> = mutableStateOf(GetStatus.NoData(""))
    val listCollector: MutableState<GetStatus<List<Store>>> = mutableStateOf(GetStatus.NoData(""))
    val listTransaction: MutableState<GetStatus<List<Transaction>>> = mutableStateOf(GetStatus.NoData(""))
    val listTenant: MutableState<GetStatus<List<Store>>> = mutableStateOf(GetStatus.NoData(""))
    val currentUser:MutableState<User?> = mutableStateOf(null)

    //store
    var storeName :MutableState<String> = mutableStateOf("")
    var storeAddress :MutableState<String> = mutableStateOf("")
    var storeDescription :MutableState<String> = mutableStateOf("")
    var storePhoneNumber :MutableState<String> = mutableStateOf("")
    val storeProfileImageUrl: MutableState<String> = mutableStateOf("")
    val storeLocation: MutableState<LatLng> = mutableStateOf(LatLng(-6.206623,106.7350596))

    //product
    val productImageUrl: MutableState<String> = mutableStateOf("")
    val productFullName: MutableState<String> = mutableStateOf("")
    val productDescription :MutableState<String> = mutableStateOf("")
    val productCategory :MutableState<ProductCategory> = mutableStateOf(ProductCategory.UNKNOWN)
    val productPrice :MutableState<Double> = mutableStateOf(0.0)
    val productUnit :MutableState<UnitProduct> = mutableStateOf(UnitProduct.KG)

    //transaction
    var transactionStore :MutableState<Store> = mutableStateOf(Store())
    var transactionProduct :MutableState<Product> = mutableStateOf(Product())

    //currentTransaction
    val detailTransaction :MutableState<GetStatus<Transaction>> = mutableStateOf(GetStatus.NoData(""))

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
        userRepository.signIn(credential) { success, shouldUpdateUser, user, message ->
            userRepository.setLocalUser(user)
            finish(success, shouldUpdateUser, message)
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
            levelUser = userLevel.value
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
            Log.e("LOG","s-${it.toString()}")
            myStore.value = storeRepository.getDetailStore(it.uid)
        }?:run{
            Log.e("LOG","no user")
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
            latitude=storeLocation.value.latitude,
            longitude=storeLocation.value.longitude,
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

    fun getListTransaction(){
        getCurrentUser { hasUser, user ->
            if(hasUser){
              viewModelScope.launch {
                  listTransaction.value= transactionRepository.getMyTransactionAsBuyer(user.uid)
              }
            }
        }
    }

    fun getListTransactionAsSeller(){
        getCurrentUser { hasUser, user ->
            if(hasUser){
                viewModelScope.launch {
                    listTransaction.value= transactionRepository.getMyTransactionAsSeller(user.uid)
                }
            }
        }
    }
    fun createNewTransaction(onComplete: (success: Boolean,transactionId:String) -> Unit){
        getCurrentUser { hasUser, user ->
            if(hasUser){
                val transaction = Transaction()

                transaction.apply {
                    buyerUid =user.uid
                    sellerUid = transactionStore.value.uid
                    totalPrice = transactionProduct.value.price
                    status = StatusTransaction.WAITING
                    detail = transactionProduct.value
                    store = transactionStore.value

                    desc = ""
                    receipt = ""
                    createdAt = getTodayTimeStamp()
                    updatedAt = getTodayTimeStamp()

                }
                transactionRepository.newTransaction(transaction){
                    success, id,message ->
                    onComplete(success,id)
                }
            }
        }
    }

    fun confirmTransactionFromSeller(transactionId:String,statusTransaction: StatusTransaction,onComplete: (success: Boolean,message:String) -> Unit){
        val transaction = Transaction()
        transaction.apply {
            uid = transactionId
            status = statusTransaction
        }
        transactionRepository.updateTransaction(transaction){
            success: Boolean, message: String ->
            onComplete(success,message)
        }
    }

    fun finishTransactionFromSeller(transactionId:String,onComplete: (success: Boolean,message:String) -> Unit){
        val transaction = Transaction()
        transaction.apply {
            uid = transactionId
            status = StatusTransaction.FINISH
        }
        transactionRepository.updateTransaction(transaction){
                success: Boolean, message: String ->
            onComplete(success,message)
        }
    }
    fun finishTransactionFromBuyer(transactionId:String,onComplete: (success: Boolean,message:String) -> Unit){
        val transaction = Transaction()
        transaction.apply {
            uid = transactionId
            status = StatusTransaction.FINISH
        }
        transactionRepository.updateTransaction(transaction){
                success: Boolean, message: String ->
            onComplete(success,message)
        }
    }

    fun finishTransaction(status:StatusTransaction,onComplete: (success: Boolean) -> Unit){

    }

    fun cancelTransaction(transactionId:String,onComplete: (success: Boolean) -> Unit){

    }

    fun getDetailTransaction(id:String) = viewModelScope.launch {
        detailTransaction.value = transactionRepository.getDetailTransaction(id)
    }

    fun getChat(storeId: String): DocumentReference {
        return transactionRepository.provideChatCollection(storeId)
    }

    fun sendChat(messages:String,transaction: Transaction,onComplete: (success: Boolean) -> Unit){
        getCurrentUser { hasUser, user ->
            if(hasUser){
                val chat = ChatItem()
                chat.apply {
                    message = messages
                    createdAt = getTodayTimeStamp()
                    updatedAt= getTodayTimeStamp()
                    fromUid = user.uid
                    toUid = transaction.sellerUid
                    mimeType= mimeTypeMessage.TEXT
                    thumb=CollectionUtils.NO_DATA_DEFAULT
                }
                transactionRepository.sendChat(chat,transaction){
                        success, message ->
                }
            }
        }

    }



}
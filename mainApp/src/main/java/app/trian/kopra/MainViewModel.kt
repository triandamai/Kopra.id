package app.trian.kopra

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.*
import com.trian.component.utils.LocationLiveData
import com.trian.component.utils.getTodayTimeStamp
import com.trian.data.model.*
import com.trian.data.model.Transaction
import com.trian.data.model.network.GetStatus
import com.trian.data.remote.CollectionUtils
import com.trian.data.remote.FirestoreSource
import com.trian.data.repository.ReminderRepository
import com.trian.data.repository.StoreRepository
import com.trian.data.repository.TransactionRepository
import com.trian.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    private val reminderRepository: ReminderRepository,
    @ApplicationContext context: Context
) :ViewModel() {

    //current location
    private val locationData = LocationLiveData(context)
    fun getLocation() = locationData

    //userProfile
    val userFullName: MutableState<String> = mutableStateOf("")
    val userUsername: MutableState<String> = mutableStateOf("")
    val userAddress: MutableState<String> = mutableStateOf("")
    val userProfileImageUrl: MutableState<String> = mutableStateOf("")
    val userBornDate: MutableState<Long> = mutableStateOf(0)
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
    val listTenant: MutableState<GetStatus<List<Store>>> = mutableStateOf(GetStatus.NoData(""))
    val listReminder: MutableState<GetStatus<List<Reminder>>> = mutableStateOf(GetStatus.NoData(""))
    val currentUser:MutableState<User?> = mutableStateOf(null)
    val detailReminder:MutableState<GetStatus<Reminder>> = mutableStateOf(GetStatus.NoData(""))
    //store
    var storeName :MutableState<String> = mutableStateOf("")
    var storeAddress :MutableState<String> = mutableStateOf("")
    var storeDescription :MutableState<String> = mutableStateOf("")
    var storePhoneNumber :MutableState<String> = mutableStateOf("")
    val storeProfileImageUrl: MutableState<String> = mutableStateOf("")
    val storeLocation: MutableState<LatLng> = mutableStateOf(LatLng(-6.206623,106.7350596))
    val storeHaveVehicel: MutableState<Boolean> = mutableStateOf(false)

    //product
    val productImageUrl: MutableState<String> = mutableStateOf("")
    val productFullName: MutableState<String> = mutableStateOf("")
    val productDescription :MutableState<String> = mutableStateOf("")
    val productPrice :MutableState<Int> = mutableStateOf(0)
    val productId :MutableState<String> = mutableStateOf("")

    //
    val reminderTitle:MutableState<String> = mutableStateOf("")
    val reminderDue:MutableState<Long> = mutableStateOf(getTodayTimeStamp())
    val reminderFrom:MutableState<Long> = mutableStateOf(getTodayTimeStamp())

    //transaction
    var transactionStore :MutableState<Store> = mutableStateOf(Store())
    var transactionProduct :MutableState<Product> = mutableStateOf(Product())

    //currentTransaction

    //currentTransaction
    private var _detailTransaction = MutableLiveData<GetStatus<Transaction>>(GetStatus.Loading())
    val detailTransaction: LiveData<GetStatus<Transaction>> = _detailTransaction

    //list chat
    private var _messages = MutableLiveData(emptyList<ChatItem>().toMutableList())
    val messages: LiveData<MutableList<ChatItem>> = _messages

    //list transaction
    private var _listTransactionFinish = MutableLiveData(emptyList<Transaction>().toMutableList())
    val listTransactionFinished: LiveData<MutableList<Transaction>> = _listTransactionFinish

    //list transaction
    private var _listTransactionUnFinish = MutableLiveData(emptyList<Transaction>().toMutableList())
    val listTransactionUnFinished: LiveData<MutableList<Transaction>> = _listTransactionUnFinish

    //kurs
    private var _kursCollection = MutableLiveData(Kurs())
    val kurs:LiveData<Kurs> = _kursCollection

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

    fun updateImageProduct(productId:String, bitmap: Bitmap, finish: (success: Boolean, url: String) -> Unit) {
        storeRepository.uploadImageProduct(productId,bitmap) { s, u ->
            finish(s, u)
            if (s) {
                userProfileImageUrl.value = u
            }
        }
    }

    fun uploadImageProduct(bitmap: Bitmap, finish: (success: Boolean, url: String, id:String) -> Unit) {
        val id = storeRepository.provideProductCollection().document().id
        storeRepository.uploadImageProduct(id,bitmap) { s, u ->
            finish(s, u,id)
            if (s) {
                userProfileImageUrl.value = u
                productId.value = id
            }
        }
    }

    fun generateIdProduct(){
        val id = storeRepository.provideProductCollection().document().id
        productId.value = id
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
            haveVehicle= storeHaveVehicel.value,
            createdAt = getTodayTimeStamp(),
            updatedAt = getTodayTimeStamp()
      )
         userRepository.getCurrentUser { hasUser, user ->
            store.apply {
                type = when(user.levelUser){
                    LevelUser.TENANT -> {
                        store.apply {
                            haveVehicle=true
                        }
                        TYPE_STORE.TENANT
                    }
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
                    uid = productId.value
                    storeUid = user.uid
                    productName = productFullName.value
                    if(user.levelUser == LevelUser.COLLECTOR) {
                        unit = UnitProduct.KG
                        category = ProductCategory.COMODITI
                    }else{
                        unit = UnitProduct.HARI
                        category = ProductCategory.VEHICLE
                    }

                    price=productPrice.value
                    thumbnail=productImageUrl.value
                    description = productDescription.value
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

    fun updateProduct(productId:String,onComplete: (success: Boolean) -> Unit){

        val product = Product()
                product.apply {
                    uid = productId
                    description = productDescription.value
                    productName = productFullName.value
                    price=productPrice.value
                    thumbnail=productImageUrl.value
                    updatedAt= getTodayTimeStamp()
                }
                storeRepository.updateProduct(product = product){
                        success, message ->
                    onComplete(success)
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
            storeRepository.getListCollector()
        }catch (e:Exception){
            GetStatus.NoData(e.message!!)
        }
    }

    fun getListTenantStore()=viewModelScope.launch {
        listTenant.value = try {
            storeRepository.getListTenant()
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

    fun getKursToday(onDataChange: () -> Unit){
        storeRepository.provideKursCollection()
            .document("kurs")
            .addSnapshotListener { value, error ->
                if(error != null ) {
                    value?.let {
                        val kurs = it.toObject(Kurs::class.java)
                        _kursCollection.value = kurs
                    }
                }
            }

    }
    fun getListTransaction(onDataChange:()->Unit){
        getCurrentUser { hasUser, user ->
            if(hasUser){
                transactionRepository
                    .provideListOrderAsBuyerCollection(user.uid)
                    .whereEqualTo("buyerUid",user.uid)
                    .orderBy("createdAt", Query.Direction.DESCENDING)
                    .addSnapshotListener { value, error ->
                        val listFinished = mutableListOf<Transaction>()
                        val listUnfinished = mutableListOf<Transaction>()
                        value?.documents?.forEach {
                            val chat = it.toObject(Transaction::class.java)
                            chat?.let {
                                if(chat.status == StatusTransaction.CANCELED || chat.status == StatusTransaction.FINISH){
                                    listFinished.add(chat!!)
                                }else{
                                    listUnfinished.add(chat!!)
                                }
                            }

                        }
                        _listTransactionFinish.value = listFinished.asReversed()
                        _listTransactionUnFinish.value = listUnfinished.asReversed()
                        onDataChange()
                    }
            }
        }
    }

    fun getListTransactionAsSeller(onDataChange:()->Unit){
        getCurrentUser { hasUser, user ->
            if(hasUser){
                transactionRepository
                    .provideListOrderAsBuyerCollection(user.uid)
                    .whereEqualTo("sellerUid",user.uid)
                    .orderBy("createdAt", Query.Direction.DESCENDING)
                    .addSnapshotListener { value, error ->
                        val listFinished = mutableListOf<Transaction>()
                        val listUnfinished = mutableListOf<Transaction>()
                        value?.documents?.forEach {
                            val chat = it.toObject(Transaction::class.java)
                            chat?.let {
                                if(chat.status == StatusTransaction.CANCELED || chat.status == StatusTransaction.FINISH){
                                    listFinished.add(chat!!)
                                }else{
                                    listUnfinished.add(chat!!)
                                }
                            }

                        }
                        _listTransactionFinish.value = listFinished.asReversed()
                        _listTransactionUnFinish.value = listUnfinished.asReversed()
                        onDataChange()
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
                    product = transactionProduct.value
                    store = transactionStore.value
                    buyer = user
                    desc = ""
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

    fun finishTransactionFromSeller(transactionId:String,receipt:Bitmap,onComplete: (success: Boolean,message:String) -> Unit){

        transactionRepository.uploadReceipt(receipt,transactionId){
            success, url, message ->
            if(success){
                val transaction = Transaction()
                transaction.apply {
                    uid = transactionId
                    receiptSeller = url
                    finishFromSeller = true
                    status = StatusTransaction.FINISH
                }
                transactionRepository.updateTransaction(transaction) {
                        success: Boolean, message: String ->
                    onComplete(
                        success,
                        message
                    )
                }
            }else{
                onComplete(false,"Failed upload receipt")
            }
        }

    }
    fun finishTransactionFromBuyer(transactionId:String,receipt: Bitmap,onComplete: (success: Boolean,message:String) -> Unit){

        transactionRepository.uploadReceipt(receipt,transactionId){
                success, url, message ->
            if(success){
                val transaction = Transaction()
                transaction.apply {
                    uid = transactionId
                    receiptBuyer = url
                    finishFromBuyer = true
                    status = StatusTransaction.FINISH
                }
                transactionRepository.updateTransaction(transaction) { success: Boolean, message: String ->
                    onComplete(
                        success,
                        message
                    )
                }
            }else{
                onComplete(false,"Failed upload receipt")
            }
        }

    }



    fun getDetailTransaction(
        id:String,
        onDataChange:()->Unit
    ){
        try {
            transactionRepository.provideDetailOrderCollection(id)
                .addSnapshotListener { value, error ->
                    value?.let {
                        if(it.exists()){
                            val transaction = it.toObject(Transaction::class.java)
                            _detailTransaction.value = GetStatus.HasData(transaction)
                        }else{
                            _detailTransaction.value = GetStatus.NoData("")
                        }
                    }?:run{
                        _detailTransaction.value = GetStatus.NoData("")
                    }
                }
        }catch (e:Exception){
            _detailTransaction.value = GetStatus.NoData(e.message!!)
        }

    }

    fun getChat(storeId: String,notify:()->Unit) {

        transactionRepository
            .provideChatCollection(storeId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                    val list = mutableListOf<ChatItem>()
                   value?.documents?.forEach {
                        val chat = it.toObject(ChatItem::class.java)
                        list.add(chat!!)
                   }
                _messages.value = list.asReversed()
                notify()
            }
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
                    thumb= CollectionUtils.NO_DATA_DEFAULT
                }
                transactionRepository.sendChat(chat,transaction){
                        success, message ->
                    onComplete(success)
                }
            }
        }

    }

    fun getListReminder() = viewModelScope.launch {
        listReminder.value = reminderRepository.getListReminder()
    }

    fun getDetailReminder(reminderId: String)=viewModelScope.launch{
       detailReminder.value= reminderRepository.getDetailReminder(reminderId)
    }

    fun createReminder(onComplete: (success: Boolean) -> Unit){
        val reminder = Reminder()
        reminder.apply {
            title = reminderTitle.value
            createdAt = reminderFrom.value
            updatedAt = reminderFrom.value
            due = reminderDue.value
        }
        reminderRepository.createReminder(reminder){
            success, message ->
            onComplete(success)
        }
    }

    fun updateReminder(reminderId:String,onComplete: (success: Boolean) -> Unit){
        val reminder = Reminder()
        reminder.apply {
            uid = reminderId
            title = reminderTitle.value
            createdAt = reminderFrom.value
            updatedAt = reminderFrom.value
            due = reminderDue.value
        }
        reminderRepository.updateReminder(reminder){
                success, message ->
            onComplete(success)
        }
    }

    fun deleteReminder(reminderId:String,onComplete: (success: Boolean) -> Unit){
        reminderRepository.deleteReminder(reminderId){
            success, message ->
            onComplete(success)
        }
    }



}
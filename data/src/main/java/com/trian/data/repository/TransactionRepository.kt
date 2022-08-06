package com.trian.data.repository

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.trian.data.model.ChatItem
import com.trian.data.model.Transaction
import com.trian.data.model.network.GetStatus


interface TransactionRepository {
    suspend fun getDetailTransaction(transactionId:String): GetStatus<Transaction>
    suspend fun getMyTransactionAsSeller(sellerUId:String):GetStatus<List<Transaction>>
    suspend fun getMyTransactionAsBuyer(buyerUid:String):GetStatus<List<Transaction>>

    fun newTransaction(transaction: Transaction,onComplete:(success:Boolean,transactionId:String,message:String)->Unit)
    fun updateTransaction(transaction: Transaction,onComplete:(success:Boolean,message:String)->Unit)
    fun uploadReceipt(bitmap: Bitmap,transactionId: String,onComplete: (success: Boolean,url:String,message: String) -> Unit)
    fun sendChat(chatItem: ChatItem, transaction: Transaction, onComplete:(success:Boolean, message:String)->Unit)
    fun provideChatCollection(storeId:String): CollectionReference
    fun provideDetailOrderCollection(storeId:String): DocumentReference

    fun provideListOrderAsBuyerCollection(buyerId:String): CollectionReference
    fun provideListOrderAsSellerCollection(sellerId:String): CollectionReference
}
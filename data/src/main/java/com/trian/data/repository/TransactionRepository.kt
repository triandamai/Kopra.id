package com.trian.data.repository

import com.trian.domain.models.ChatItem
import com.trian.domain.models.Transaction
import com.trian.domain.models.network.GetStatus

interface TransactionRepository {
    suspend fun getDetailTransaction(transactionId:String):GetStatus<Transaction>
    suspend fun getMyTransactionAsSeller(sellerUId:String):GetStatus<List<Transaction>>
    suspend fun getMyTransactionAsBuyer(buyerUid:String):GetStatus<List<Transaction>>

    fun newTransaction(transaction: Transaction,onComplete:(success:Boolean,message:String)->Unit)
    fun updateTransaction(transaction: Transaction,onComplete:(success:Boolean,message:String)->Unit)
    fun sendChat(chatItem: ChatItem, onComplete:(success:Boolean, message:String)->Unit)
}
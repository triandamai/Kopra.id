package com.trian.data.repository

import com.trian.domain.models.ChatItemModel
import com.trian.domain.models.Transaction
import com.trian.domain.models.network.GetStatus
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getDetailTransaction():GetStatus<Transaction>
    fun getMyTransactionAsTenant():GetStatus<List<Transaction>>
    fun getMyTransactionAsFarmer():GetStatus<List<Transaction>>
    fun getMyTransactionAsCollector():GetStatus<List<Transaction>>

    fun newTransaction(transaction: Transaction,onComplete:(success:Boolean,message:String)->Unit)
    fun updateTransaction(transaction: Transaction,onComplete:(success:Boolean,message:String)->Unit)
    fun sendChat(chatItemModel: ChatItemModel,onComplete:(success:Boolean,message:String)->Unit)
}
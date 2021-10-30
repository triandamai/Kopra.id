package com.trian.data.repository

import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.ChatItem
import com.trian.domain.models.Transaction
import com.trian.domain.models.network.GetStatus

class TransactionRepositoryImpl(
    private val source: FirestoreSource
) :TransactionRepository{
    override fun getDetailTransaction(): GetStatus<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getMyTransactionAsTenant(): GetStatus<List<Transaction>> {
        TODO("Not yet implemented")
    }

    override fun getMyTransactionAsFarmer(): GetStatus<List<Transaction>> {
        TODO("Not yet implemented")
    }

    override fun getMyTransactionAsCollector(): GetStatus<List<Transaction>> {
        TODO("Not yet implemented")
    }

    override fun newTransaction(
        transaction: Transaction,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(
        transaction: Transaction,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun sendChat(
        chatItem: ChatItem,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
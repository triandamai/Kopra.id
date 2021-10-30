package com.trian.data.repository

import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.ChatItem
import com.trian.domain.models.Transaction
import com.trian.domain.models.network.GetStatus
import com.trian.domain.models.toUpdatedData
import kotlinx.coroutines.tasks.await

class TransactionRepositoryImpl(
    private val source: FirestoreSource
) :TransactionRepository{
    override suspend fun getDetailTransaction(transactionId: String): GetStatus<Transaction> {
        return try {
            val result = source.transactionCollection().document(transactionId).get().await().toObject(Transaction::class.java)
            GetStatus.HasData(result)
        }catch (e:Exception){
            GetStatus.NoData(e.message!!)
        }
    }

    override suspend fun getMyTransactionAsSeller(sellerUId: String): GetStatus<List<Transaction>> {
        return try {
            val result = source.transactionCollection()
                .whereEqualTo("sellerUid",sellerUId)
                .get()
                .await()
            val transform = result.documents.map {
                it.toObject(Transaction::class.java)!!
            }
            GetStatus.HasData(transform)
        }catch (e:Exception){
            GetStatus.NoData(e.message!!)
        }
    }

    override suspend fun getMyTransactionAsBuyer(buyerUid: String): GetStatus<List<Transaction>> {
        return try {
            val result = source.transactionCollection()
                .whereEqualTo("buyerUid",buyerUid)
                .get()
                .await()
            val transform = result.documents.map {
                it.toObject(Transaction::class.java)!!
            }
            GetStatus.HasData(transform)
        }catch (e:Exception){
            GetStatus.NoData(e.message!!)
        }
    }

    override fun newTransaction(
        transaction: Transaction,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        val id = source.transactionCollection().document().id
        transaction.apply {
            uid = id
        }
        source.transactionCollection().document(id)
            .set(transaction)
            .addOnCompleteListener {
                onComplete(true,"")
            }
            .addOnFailureListener {
                onComplete(false,"")
            }
    }

    override fun updateTransaction(
        transaction: Transaction,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        source.transactionCollection()
            .document(transaction.uid)
            .update(transaction.toUpdatedData())
            .addOnCompleteListener {
                onComplete(true,"")
            }
            .addOnFailureListener {
                onComplete(false,"")
            }
    }

    override fun sendChat(
        chatItem: ChatItem,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {

    }
}
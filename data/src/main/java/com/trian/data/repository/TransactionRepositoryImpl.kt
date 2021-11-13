package com.trian.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.trian.common.utils.utils.CollectionUtils
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.ChatItem
import com.trian.domain.models.Transaction
import com.trian.domain.models.network.GetStatus
import com.trian.domain.models.toStatusUpdate
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
        onComplete: (success: Boolean,transactionId:String, message: String) -> Unit
    ) {
        val id = source.transactionCollection().document().id
        transaction.apply {
            uid = id
        }
        source.transactionCollection().document(id)
            .set(transaction)
            .addOnCompleteListener {
                onComplete(true,id,"")
            }
            .addOnFailureListener {
                onComplete(false,"","")
            }
    }

    override fun updateTransaction(
        transaction: Transaction,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        source.transactionCollection()
            .document(transaction.uid)
            .update(transaction.toStatusUpdate(transaction.status))
            .addOnCompleteListener {
                onComplete(true,"")
            }
            .addOnFailureListener {
                onComplete(false,"")
            }
    }


    override fun sendChat(
        chatItem: ChatItem,
        transaction: Transaction,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        chatItem.apply {
            uid
        }
        var id = source.chatCollection().document().id

        source.chatCollection()
            .document(transaction.uid)
            .collection(transaction.uid)
            .document().set(chatItem)
            .addOnFailureListener { onComplete(false,"") }
            .addOnSuccessListener { onComplete(true,"") }
    }

    override fun provideChatCollection(transactionId: String): DocumentReference {
        return source
            .chatCollection()
            .document(transactionId)
            .collection(transactionId)
            .document()
    }
}
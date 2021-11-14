package com.trian.data.repository

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.trian.common.utils.utils.CollectionUtils
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.ChatItem
import com.trian.domain.models.Transaction
import com.trian.domain.models.network.GetStatus
import com.trian.domain.models.toStatusUpdate
import com.trian.domain.models.toUpdatedData
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

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

    override fun uploadReceipt(
        bitmap: Bitmap,
        transactionId: String,
        onComplete: (success: Boolean, url: String, message: String) -> Unit
    ) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val storageReference = source
            .storageStore()
            .child("RECEIPT-$transactionId")

        storageReference.putBytes(data)
            .continueWith {
                    task->
                if(!task.isSuccessful){
                    task.exception?.let {
                        throw  it
                    }
                }
                storageReference.downloadUrl

            }
            .addOnCompleteListener {
                    task->
                if(task.isSuccessful){
                    val url = task.result
                    url!!.addOnSuccessListener {
                            uri->
                        onComplete(true,uri.toString(),"")
                    }.addOnFailureListener {e->
                        onComplete(false,"",e.message!!)
                    }
                }else{
                    onComplete(false,"","task not success")
                }

            }.addOnFailureListener {
                onComplete(false,"","")
            }



    }


    override fun sendChat(
        chatItem: ChatItem,
        transaction: Transaction,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        var id = source.transactionCollection().document().id
        chatItem.apply {
            uid = id
        }


        source.transactionCollection()
            .document(transaction.uid)
            .collection(CollectionUtils.CHAT)
            .document(id).set(chatItem)
            .addOnFailureListener { onComplete(false,"") }
            .addOnSuccessListener { onComplete(true,"") }
    }

    override fun provideChatCollection(transactionId: String): CollectionReference {
        return source.transactionCollection()
            .document(transactionId)
            .collection(CollectionUtils.CHAT)

    }

    override fun provideDetailOrderCollection(transactionId: String): CollectionReference {
        return source.transactionCollection()
    }

    override fun provideListOrderAsBuyerCollection(buyerId: String): CollectionReference {
        return source.transactionCollection()

    }

    override fun provideListOrderAsSellerCollection(sellerId: String): CollectionReference {
        return source
            .transactionCollection()

    }

}
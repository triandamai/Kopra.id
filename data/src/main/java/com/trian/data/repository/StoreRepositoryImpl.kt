package com.trian.data.repository

import com.google.firebase.firestore.Query
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.Product
import com.trian.domain.models.Store
import com.trian.domain.models.network.GetStatus
import com.trian.domain.models.toUpdatedData
import kotlinx.coroutines.tasks.await

class StoreRepositoryImpl(
    private val source: FirestoreSource
):StoreRepository {
    override fun createStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit) {
        val id = source.storeCollection().document().id
        store.apply {
            uid = id
        }

        source.storeCollection().document(id)
            .set(store)
            .addOnCompleteListener {
                onComplete(true,"")
            }.addOnFailureListener {
                onComplete(false,"")
            }
    }

    override fun updateStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit) {
        source.storeCollection().document(store.uid)
            .update(store.toUpdatedData())
            .addOnCompleteListener {
                onComplete(true,"")
            }.addOnFailureListener {
                onComplete(false,"")
            }
    }

    override suspend fun getListProductByStore(storeId: String): GetStatus<List<Product>> {
       return try {
           val result = source.productCollection()
               .whereEqualTo("storeUid",storeId)
               .orderBy("createdAt",Query.Direction.ASCENDING)
               .get()
               .await()
           val products = result.documents.map {
               it.toObject(Product::class.java)!!
           }
           GetStatus.HasData(products)
       }catch (e:Exception){
           GetStatus.NoData(e.message!!)
       }

    }


}
package com.trian.data.repository

import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.Product
import com.trian.domain.models.Store
import com.trian.domain.models.network.GetStatus

class StoreRepositoryImpl(
    private val source: FirestoreSource
):StoreRepository {
    override fun createStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun updateStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getListProductAsBuyer(storeId: String): GetStatus<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getListProductAsOwner(storeId: String): GetStatus<List<Product>> {
        TODO("Not yet implemented")
    }


}
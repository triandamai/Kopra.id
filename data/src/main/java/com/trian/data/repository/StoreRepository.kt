package com.trian.data.repository

import com.trian.domain.models.Product
import com.trian.domain.models.ProductTransaction
import com.trian.domain.models.Store
import com.trian.domain.models.network.GetStatus

interface StoreRepository {
    fun createStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit)
    fun updateStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit)

    fun getListProductAsBuyer(storeId:String):GetStatus<List<Product>>
    fun getListProductAsOwner(storeId:String):GetStatus<List<Product>>


}
package com.trian.data.repository

import android.graphics.Bitmap
import com.trian.domain.models.Product
import com.trian.domain.models.ProductTransaction
import com.trian.domain.models.Store
import com.trian.domain.models.network.GetStatus

interface StoreRepository {

    suspend fun getListProductByStore(storeId:String):GetStatus<List<Product>>

    fun createStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit)
    fun updateStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit)

    fun uploadBanner(bitmap: Bitmap,onComplete: (success: Boolean, url: String) -> Unit)
    fun uploadLogo(bitmap: Bitmap,onComplete: (success: Boolean, url: String) -> Unit)

}
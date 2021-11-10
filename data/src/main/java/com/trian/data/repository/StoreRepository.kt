package com.trian.data.repository

import android.graphics.Bitmap
import com.trian.domain.models.Product

import com.trian.domain.models.ProductTransaction
import com.trian.domain.models.Store
import com.trian.domain.models.network.GetStatus

interface StoreRepository {

    suspend fun getListProductByStore(storeId:String):GetStatus<List<Product>>
    suspend fun getDetailProduct(productId:String):GetStatus<Product>

    suspend fun getListStore():GetStatus<List<Store>>
    suspend fun getDetailStore(storeId:String):GetStatus<Store>

    fun createProduct(product: Product, onComplete: (success: Boolean, message: String) -> Unit)
    fun updateProduct(product: Product,onComplete: (success: Boolean, message: String) -> Unit)

    fun createStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit)
    fun updateStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit)

    fun uploadBanner(bitmap: Bitmap,onComplete: (success: Boolean, url: String) -> Unit)
    fun uploadLogo(bitmap: Bitmap,onComplete: (success: Boolean, url: String) -> Unit)

   suspend fun getDetailProductForCheckOut(productId: String,onComplete: (success: Boolean, product: Product) -> Unit)
   suspend fun getDetailStoreForCheckOut(storeId: String,onComplete: (success: Boolean, product: Store) -> Unit)
}
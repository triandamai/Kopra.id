package com.trian.data.repository

import android.graphics.Bitmap
import com.google.firebase.firestore.CollectionReference
import com.trian.data.model.Product
import com.trian.data.model.Store
import com.trian.data.model.network.GetStatus

interface StoreRepository {

    suspend fun getListProductByStore(storeId:String): GetStatus<List<Product>>
    suspend fun getDetailProduct(productId:String):GetStatus<Product>

    suspend fun getListStore():GetStatus<List<Store>>
    suspend fun getListTenant():GetStatus<List<Store>>
    suspend fun getListCollector():GetStatus<List<Store>>
    suspend fun getDetailStore(storeId:String):GetStatus<Store>

    fun provideProductCollection():CollectionReference
    fun provideKursCollection():CollectionReference

    fun createProduct(product: Product, onComplete: (success: Boolean, message: String) -> Unit)
    fun updateProduct(product: Product,onComplete: (success: Boolean, message: String) -> Unit)
    fun deleteProduct(productId: String,onComplete: (success: Boolean, url: String) -> Unit)
    fun createStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit)
    fun updateStore(store: Store, onComplete: (success: Boolean, url: String) -> Unit)

    fun uploadBanner(bitmap: Bitmap,onComplete: (success: Boolean, url: String) -> Unit)
    fun uploadLogo(bitmap: Bitmap,onComplete: (success: Boolean, url: String) -> Unit)
    fun uploadImageProduct(productId:String,bitmap: Bitmap,onComplete: (success: Boolean, url: String) -> Unit)

   suspend fun getDetailProductForCheckOut(productId: String,onComplete: (success: Boolean, product: Product) -> Unit)
   suspend fun getDetailStoreForCheckOut(storeId: String,onComplete: (success: Boolean, product: Store) -> Unit)
}
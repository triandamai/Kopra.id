package com.trian.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.trian.common.utils.network.DataOrException
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */


class UserRepositoryImpl(
    private val  source: FirestoreSource
):UserRepository {
    override suspend fun getUserById(id:String): DataOrException<User, Exception> {
        val dataOrException = DataOrException<User, Exception>()
        try {
          dataOrException.data = source.userCollection().document(id).get().await().toObject(User::class.java)
        }catch (e:Exception){
            dataOrException.e = e
        }
        return dataOrException
    }
}
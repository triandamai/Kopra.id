package com.trian.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 21/10/2021
 */

object DataRemote{
    private const val TAG = ""

    suspend fun getProfile():Any{
        val db = FirebaseFirestore.getInstance()
        return try {
            db.collection("")
                .document("")
                .get().await()
        }catch (e:Exception){

        }
    }
}
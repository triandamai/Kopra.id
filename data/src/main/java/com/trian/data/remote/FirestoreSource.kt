package com.trian.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.trian.common.utils.utils.CollectionUtils

class FirestoreSource(
    val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
    ) {

    fun userCollection():CollectionReference = firebaseFirestore.collection(CollectionUtils.USER)
    fun storeCollection():CollectionReference = firebaseFirestore.collection(CollectionUtils.STORE)
    fun transactionCollection():CollectionReference = firebaseFirestore.collection(CollectionUtils.TRANSACTION)

}
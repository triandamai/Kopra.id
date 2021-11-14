package com.trian.data.repository

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.trian.data.remote.FirestoreSource
import com.trian.domain.models.Product
import com.trian.domain.models.Reminder


import com.trian.domain.models.Store
import com.trian.domain.models.network.GetStatus
import com.trian.domain.models.toUpdatedData
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class ReminderRepositoryImpl(
    private val source: FirestoreSource
):ReminderRepository {
    override suspend fun getListReminder(): GetStatus<List<Reminder>> {
        return try {
          val uid = source.firebaseAuth.currentUser?.uid ?: ""
            val reminders = source
                .reminderCollection()
                .whereEqualTo("userUid",uid)
                .orderBy("createdAt",Query.Direction.ASCENDING)
                .get()
                .await()
            val transform = reminders.map {
                it.toObject(Reminder::class.java)
            }

           if(transform.isNullOrEmpty()){

               GetStatus.NoData("")
           }else {
               GetStatus.HasData(transform)

           }
        }catch (e:Exception){

            GetStatus.NoData(e.message!!)
        }
    }

    override fun createReminder(
        reminder: Reminder,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        source.firebaseAuth.currentUser?.let {
            val id = source.reminderCollection().document().id
            reminder.apply {
                uid=id
                userUid = it.uid
            }
            source.reminderCollection()
                .document(id)
                .set(reminder)
                .addOnSuccessListener {
                    Log.e("reminderrepo58","hehe")
                    onComplete(true,"")
                }.addOnFailureListener {
                    Log.e("reminderrepo58",it.message!!)
                    onComplete(false,it.message!!)
                }
        }
    }

    override fun updateReminder(
        reminder: Reminder,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        source.reminderCollection()
            .document(reminder.uid)
            .set(reminder.toUpdatedData(), SetOptions.merge())
            .addOnSuccessListener {
                onComplete(true,"")
            }.addOnFailureListener {
                onComplete(false,it.message!!)
            }
    }

    override fun deleteReminder(
        reminderId: String,
        onComplete: (success: Boolean, message: String) -> Unit
    ) {
        source.reminderCollection()
            .document(reminderId)
            .delete()
            .addOnSuccessListener {
                onComplete(true,"")
            }.addOnFailureListener {
                onComplete(false,it.message!!)
            }
    }

}
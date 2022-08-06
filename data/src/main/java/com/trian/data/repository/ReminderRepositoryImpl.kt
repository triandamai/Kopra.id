package com.trian.data.repository

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.trian.data.model.Reminder
import com.trian.data.model.network.GetStatus
import com.trian.data.model.toUpdatedData
import com.trian.data.remote.FirestoreSource

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

    override suspend fun getDetailReminder(reminderId: String): GetStatus<Reminder> {
        return try {
            val reminder = source
                .reminderCollection()
                .document(reminderId)
                .get()
                .await()
                .toObject(Reminder::class.java)

            Log.e("reminderImpl57",reminder.toString())
            reminder?.let {
                GetStatus.HasData(reminder)
            }?:run{
                GetStatus.NoData("No data found")
            }
        }catch (e:Exception){
            Log.e("reminderImpl64",e.message!!)
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
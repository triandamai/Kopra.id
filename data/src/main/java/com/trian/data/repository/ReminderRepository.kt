package com.trian.data.repository

import android.graphics.Bitmap
import com.google.firebase.firestore.CollectionReference
import com.trian.domain.models.Product
import com.trian.domain.models.Reminder


import com.trian.domain.models.Store
import com.trian.domain.models.network.GetStatus

interface ReminderRepository {
    suspend fun getListReminder():GetStatus<List<Reminder>>
    suspend fun  getDetailReminder(reminderId: String):GetStatus<Reminder>
    fun createReminder(reminder: Reminder,onComplete:(success:Boolean,message:String)->Unit)
    fun updateReminder(reminder: Reminder,onComplete:(success:Boolean,message:String)->Unit)
    fun deleteReminder(reminderId: String,onComplete:(success:Boolean,message:String)->Unit)
}
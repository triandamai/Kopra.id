package com.trian.data.repository

import com.trian.data.model.Reminder
import com.trian.data.model.network.GetStatus


interface ReminderRepository {
    suspend fun getListReminder(): GetStatus<List<Reminder>>
    suspend fun  getDetailReminder(reminderId: String):GetStatus<Reminder>
    fun createReminder(reminder: Reminder,onComplete:(success:Boolean,message:String)->Unit)
    fun updateReminder(reminder: Reminder,onComplete:(success:Boolean,message:String)->Unit)
    fun deleteReminder(reminderId: String,onComplete:(success:Boolean,message:String)->Unit)
}
package com.trian.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
/**
 * Local Database Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

@Database(
    entities = [User::class,Measurement::class,Nurse::class],
    version = 25,
    exportSchema = false
)
abstract class CexupDatabase :RoomDatabase(){
    abstract fun userDao():UserDao
    abstract fun nurseDao():NurseDao
    abstract fun measurementDao():MeasurementDao
    companion object{
        const val DATABASE_NAME = "cexup_db"
    }
}
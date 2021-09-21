package com.trian.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.trian.domain.entities.User
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: User?): Long

    @Update
    fun updatePatient(patient: User?)

    @Delete
    fun deletePatient(patient: User?)


    @Transaction
    fun patientTransaction(patients: List<User>){
        patients.forEach { patient ->
            if(checkExist(patient.id_user,patient.no_type) < 1){
                insertPatient(patient)
            }else{
                updatePatient(patient)
            }
        }
    }
    @Query("SELECT COUNT(*) FROM tb_users WHERE id_user = :id_user AND no_type = :created_at")
    fun checkExist(id_user: Int?,created_at:String?):Int
}
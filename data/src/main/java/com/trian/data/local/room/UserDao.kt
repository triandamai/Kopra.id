package com.trian.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.trian.domain.entities.User

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface UserDao {
    @Query("SELECT * FROM tb_users")
    suspend fun getAll():List<User>

    @Insert
    suspend fun insert(users:List<User>)

    @Query("SELECT DISTINCT *  FROM tb_users group by user_id")
    fun allPatient(): LiveData<List<User?>?>?

    @Query("SELECT DISTINCT *  FROM tb_users group by user_id")
    fun all(): List<User>?

    @Query("SELECT * FROM tb_users WHERE name LIKE :q OR  no_type = :q")
    fun getLikeName(q: String?): List<User?>?

    @Query("SELECT  COUNT(user_id)  FROM tb_users WHERE user_id = :userid")
    fun count(userid: Int): Int

    @Query("DELETE FROM tb_users")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: User?): Long

    @Insert
    fun insertPatientLocal(patient: User?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun batchInsert(patients: List<User?>?)

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
package library.data.storage.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import library.data.storage.room.entity.Patient

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface PatientDao {
    @Query("SELECT DISTINCT *  FROM tb_patient group by user_id")
    fun allPatient(): LiveData<List<Patient?>?>?

    @Query("SELECT DISTINCT *  FROM tb_patient group by user_id")
    fun all(): List<Patient?>?

    @Query("SELECT * FROM tb_patient WHERE name LIKE :q OR  no_type = :q")
    fun getLikeName(q: String?): List<Patient?>?

    @Query("SELECT  COUNT(user_id)  FROM tb_patient WHERE user_id = :userid")
    fun count(userid: Int): Int

    @Query("DELETE FROM tb_patient")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: Patient?): Long

    @Insert
    fun insertPatientLocal(patient: Patient?): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun batchInsert(patients: List<Patient?>?)

    @Update
    fun updatePatient(patient: Patient?)

    @Delete
    fun deletePatient(patient: Patient?)
}
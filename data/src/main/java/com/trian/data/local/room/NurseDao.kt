package com.trian.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.trian.domain.entities.Nurse
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface NurseDao {
    @Query("SELECT * FROM tb_nurse")
    suspend fun allNurse(): List<Nurse>

    @Insert
    fun insert(nurse: Nurse?)

    @Update
    fun update(nurse: Nurse?)

    @Delete
    fun delete(nurse: Nurse?)
}
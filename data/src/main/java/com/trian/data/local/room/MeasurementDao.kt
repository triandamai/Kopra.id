package com.trian.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.trian.domain.entities.Measurement

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface MeasurementDao {
    @Query("SELECT * FROM tb_measurement")
    suspend fun allCheckUp(): List<Measurement>

    @Query("SELECT * FROM tb_measurement WHERE id_patient = :id_patient")
    fun getLastCheckUpById(id_patient: String?): LiveData<List<Measurement?>?>?

    @Query("SELECT COUNT(id) FROM tb_measurement WHERE type = :type AND  id_patient = :id")
    fun getCount(type: Int, id: String?): Int

    @Query("SELECT * FROM tb_measurement WHERE type = :type AND id_patient = :idpatient")
    fun getHistory(type: Int, idpatient: String?): List<Measurement?>?

    @Query("SELECT  *  FROM tb_measurement WHERE type = :type AND id_patient = :id_patient AND timestamp BETWEEN :from AND :to GROUP BY timestamp,type ")
    fun getHistoryBydate(type:Int,id_patient: String,from:Long,to:Long):List<Measurement?>?

    @Transaction
    fun measureTransaction(measurements: List<Measurement>, isUploaded:Boolean){
        measurements.forEach { measurement ->
            measurement.is_upload = isUploaded
            if(checkExist(measurement.type!!,measurement.created_at!!) < 1){
                insert(measurement)
            }else{
                update(measurement)
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBatch(measurement:List<Measurement>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(measurement: Measurement?)

    @Update
    fun update(measurement: Measurement?)

    @Delete
    fun delete(measurement: Measurement?)

    @Query("SELECT COUNT(*) FROM tb_measurement WHERE type = :type AND created_at = :created_at")
    fun checkExist(type: Int,created_at:String):Int
}
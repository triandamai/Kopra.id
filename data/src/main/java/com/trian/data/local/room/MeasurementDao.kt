package com.trian.data.local.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.trian.domain.entities.Measurement
import kotlinx.coroutines.flow.Flow

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Dao
interface MeasurementDao {

    @Query("SELECT * FROM tb_measurement WHERE member_id = :member_id AND is_upload= :is_upload")
    fun getNotUploaded(member_id: String,is_upload:Boolean=false):List<Measurement>

    @Query("SELECT * FROM tb_measurement WHERE member_id = :member_id")
    fun getLastCheckUpById(member_id: String?): LiveData<List<Measurement?>?>?

    @Query("SELECT COUNT(id) FROM tb_measurement WHERE type = :type AND  member_id = :id")
    fun getCount(type: Int, id: String?): Int

    @Query("SELECT * FROM tb_measurement WHERE type = :type AND member_id = :member_id AND created_at BETWEEN :from AND :to ")
    fun getHistoryByDate(type:Int,member_id: String,from:Long,to:Long):List<Measurement>

    @Query("SELECT * FROM tb_measurement WHERE type = :type AND member_id = :member_id ORDER BY created_at DESC LIMIT 1")
    fun getLastMeasurement(type: Int,member_id: String):List<Measurement>

    @Transaction
    suspend fun measureTransaction(measurements: List<Measurement>, isUploaded:Boolean){
        measurements.forEach { measurement ->
            measurement.is_upload = isUploaded
            if(checkExist(measurement.type,measurement.created_at) < 1){
                Log.e("DAO","Not Exist")
                insert(measurement)
            }else{
                update(measurement)
                Log.e("DAO","Exist")
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(measurement: Measurement?)

    @Update
    fun update(measurement: Measurement?)

    @Delete
    fun delete(measurement: Measurement?)

    @Query("SELECT COUNT(*) FROM tb_measurement WHERE type = :type AND created_at = :created_at")
    fun checkExist(type: Int,created_at:Long):Int
}
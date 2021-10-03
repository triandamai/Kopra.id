package com.trian.data.local.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.trian.common.utils.sdk.SDKConstant
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

    @Query("SELECT * FROM tb_measurement")
    fun getAll():List<Measurement>

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

            val checkExist = getDataExist(measurement.type,measurement.created_at)

            checkExist?.let {

                //update because the data is already exist
                it.apply {
                    is_upload = isUploaded
                }

                update(it)
            }?:run{

                //insert if doesn't exist
                measurement.apply {
                    is_upload = isUploaded
                }
                insert(measurement)
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(measurement: Measurement)

    @Update
    fun update(measurement: Measurement)

    @Delete
    fun delete(measurement: Measurement)

    @Query("SELECT COUNT(*) FROM tb_measurement WHERE type = :type AND created_at = :created_at")
    fun checkExist(type: Int,created_at:Long):Int

    @Query("SELECT * FROM tb_measurement WHERE type = :type AND created_at = :created_at")
    fun getDataExist(type: Int,created_at:Long):Measurement?
}
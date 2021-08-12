package library.data.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import library.data.storage.room.dao.MeasurementDao
import library.data.storage.room.dao.NurseDao
import library.data.storage.room.dao.PatientDao
import library.data.storage.room.entity.Measurement
import library.data.storage.room.entity.Nurse
import library.data.storage.room.entity.Patient


/**
 * Database Local
 */
@Database(
    entities = [Patient::class, Measurement::class, Nurse::class],
    exportSchema = false,
    version = 23
)
abstract class DataStorage : RoomDatabase() {
    abstract fun patientDao(): PatientDao?
    abstract fun measurementDao(): MeasurementDao?
    abstract fun nurseDao(): NurseDao?

    companion object {
        private const val DB_NAME = "device_cexup"
        private var instance: DataStorage? = null
        @Synchronized
        fun getInstance(context: Context): DataStorage? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataStorage::class.java, DB_NAME
                ).fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}

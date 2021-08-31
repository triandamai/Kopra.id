package com.trian.module.data


import com.google.gson.Gson
import com.trian.data.local.room.CexupDatabase
import com.trian.data.local.room.MeasurementDao
import com.trian.data.local.room.NurseDao
import com.trian.data.local.room.UserDao
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.BloodPressureModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject
import javax.inject.Named


@HiltAndroidTest
@Config(application = HiltTestApplication::class,sdk = [29])
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class CexupDatabaseTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    @Named("test_db")
    lateinit var database: CexupDatabase
    @Inject
    lateinit var gson: Gson
    private lateinit var userDao: UserDao
    private lateinit var nurseDao: NurseDao
    private lateinit var measurementDao: MeasurementDao

    @Before
    fun setup(){
        hiltRule.inject()
        userDao = database.userDao()
        nurseDao = database.nurseDao()
        measurementDao = database.measurementDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun `should insert user to local`()  = runBlocking{
        //given
        val user = User(
            id_user = null,
            user_id="ini user id",
            type="ini type " ,
            no_type="ini no type" ,
            doctor_id="ini id doctor",
            speciality_id="ini specialty id",
            hospital_active="ini hospital active",
            name="ini name",
            username="ini username",
            gender="ini gender",
            email="ini email",
            phone_number="ini phone" ,
            address="ini address" ,
            thumb="ini thumb"
        )
        userDao.insertPatient(user)
        //when
        val allUsers = userDao.getAll()
        //then( user jika default == null maka akan autogenerate)
        user.id_user = 1
        assertEquals(listOf(user),allUsers)
    }
    @Test
    fun `should insert nurse to local`()= runBlocking {
        //given
        val nurse = Nurse(
            id = null,
            name= "ini nama",
            email="ini email",
            gender="ini gender" ,
            phoneNumber="ini phone number" ,
            type="ini type",
            no_type="ini no type",
            address="ini adresss"
        )
        nurseDao.insert(nurse)
        //when
        val allNurse = nurseDao.allNurse()
        //then
        nurse.id = 1
        assertEquals(listOf(nurse),allNurse)
    }

    @Test
    fun `should insert measurement to local`() = runBlocking{
        //given
        val measurement = Measurement(
            id= null,
            id_patient="ini patient",
            device_id="ini device id",
            type=1,
            result="{ini result}",
            asset="kosong",
            created_at="2020",
            test_method="automatic",
            timestamp= 316352,
            is_upload=false,
        )
        measurementDao.insert(measurement)
        //when
        val allMeasurement = measurementDao.allCheckUp()
        //then
        measurement.id = 1
        assertEquals(listOf(measurement),allMeasurement)

    }

    @Test
    fun `should convert bpm model to json or otherwise `(){
        //check
        val json:String = """
            {"systole":121.0,"diastole":80.0,"pulse":0.0,"method":"automatic","timestamp":0}
            """.trimIndent()
        //given
        val bpm = BloodPressureModel(systole = 121f,diastole = 80f)
        val bpmToJson =gson.toJson(bpm)
        //when
        val measurement = Measurement(
            id= null,
            id_patient="ini patient",
            device_id="ini device id",
            type=1,
            result=bpmToJson,
            asset="kosong",
            created_at="2020",
            test_method="automatic",
            timestamp= 316352,
            is_upload=false,
        )
        //then
        val fromJson = gson.fromJson(measurement.result, BloodPressureModel::class.java)
        assertEquals(json,bpmToJson)
        assertEquals(bpm,fromJson)
    }
}

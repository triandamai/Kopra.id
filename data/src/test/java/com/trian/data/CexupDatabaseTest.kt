package com.trian.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.trian.data.local.room.CexupDatabase
import com.trian.data.local.room.UserDao
import com.trian.domain.entities.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.LooperMode
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class UserDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: CexupDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup(){
        hiltRule.inject()
        userDao = database.userDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertUser()  {
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
        val allUsers = userDao.all()
        assertThat(allUsers,contains(user))
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestDataModule {
        @Provides
        @Named("test_db")
        fun provideInMemoryDb(@ApplicationContext context: Context) =
            Room.inMemoryDatabaseBuilder(
                context, CexupDatabase::class.java
            ).allowMainThreadQueries()
                .build()
    }
}


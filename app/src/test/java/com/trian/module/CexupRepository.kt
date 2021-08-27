package com.trian.module

import com.google.gson.Gson
import com.trian.common.utils.network.NetworkStatus
import com.trian.data.local.room.CexupDatabase
import com.trian.data.local.room.MeasurementDao
import com.trian.data.local.room.NurseDao
import com.trian.data.local.room.UserDao
import com.trian.data.repository.CexupRepository
import com.trian.domain.entities.Measurement
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import com.trian.domain.models.BloodPressureModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.*
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
    class CexupRepositoryTest {

        @get:Rule
        var hiltRule = HiltAndroidRule(this)


        @Inject
        lateinit var repository: CexupRepository
        @Inject
        lateinit var gson: Gson

        @Before
        fun setup() {
            hiltRule.inject()

        }

        @Test
        fun `userShouldInsert`()  = runBlocking{
            val fetchUsers = repository.fetchAllUsers()
            assertEquals("ss",fetchUsers.errorMessage)
        }

    }


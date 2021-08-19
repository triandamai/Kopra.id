package com.cexup_sdk

import com.cexup_sdk.services.Api
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ApiTests {
    private val apiMockEngine = ApiMockEngine()
    private val apiMock = Api()
    val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test posts login`() =  testDispatcher.runBlockingTest {


//        //when
//        val expected = """
//           {
//            "success":true,
//            "message":"success"
//           }
//        """.trimIndent()
//        val result = apiMock.login("", "")
//        result contentEquals expected

    }
}
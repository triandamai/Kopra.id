package com.cexup_sdk

import com.cexup_sdk.services.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals

import org.junit.Test

class ApiTests{
    private val apiMockEngine = ApiMockEngine()
    private val apiMock = Api(apiMockEngine.get())
    private val testScope = TestCoroutineScope(TestCoroutineDispatcher())

    @ExperimentalCoroutinesApi
    @Test
    fun `test posts login`() {
        testScope.launch {
            apiMock.login("","")
        }

    }

}
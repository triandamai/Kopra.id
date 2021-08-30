package com.trian.module.data

import com.trian.data.remote.app.AppApiServices
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.domain.repository.BaseResponse
import com.trian.domain.entities.User
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit




class AppRemoteDataSourceTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AppApiServices::class.java)



    private val sut = AppRemoteDataSource(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should success login user`() {
        mockWebServer.enqueueResponse("response-login-200.json", 200)

        runBlocking {
            val actual = sut.loginUser()
            val expected = BaseResponse<List<User>>(true, listOf<User>(),"Berhasil Login")
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should success login nurse`() {
        mockWebServer.enqueueResponse("response-login-200.json", 200)

        runBlocking {
            val actual = sut.loginNurse()
            val expected = BaseResponse<List<User>>(true, listOf<User>(),"Berhasil Login")
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should failed request`() {
        mockWebServer.enqueueResponse("response-failed.json", 200)

        runBlocking {
            val actual = sut.loginNurse()
            val expected = BaseResponse<List<User>>(true, listOf<User>(),"Berhasil Login")
            assertNotSame(expected, actual)
        }
    }

    @Test
    fun `should get list article`(){}

    @Test
    fun `should get list doctor`(){}

    @Test
    fun `should send booking doctor`(){}

    @Test
    fun `should get list order`(){}

    @Test
    fun `should detail order`(){}

    @Test
    fun `should get list available doctor time`(){}

    @Test
    fun `should get list speciality`(){}

    @Test
    fun `should get detail room meeting`(){}

    @Test
    fun `should get health status`(){}

    @Test
    fun `should get list hospital`(){}

    @Test
    fun `should get list product`(){}

    @Test
    fun `should get list personal record`(){}

    @Test
    fun `should send measurement`(){}
}
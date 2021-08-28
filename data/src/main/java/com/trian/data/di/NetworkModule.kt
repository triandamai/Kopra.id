package com.trian.data.di

import com.trian.data.remote.app.AppApiServices
import com.trian.data.remote.device.DeviceApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
object NetworkModule {
    private val REQUEST_TIMEOUT = 10
    private var  okHttpClient:OkHttpClient? = null

    @Provides
    internal fun httpLoggingInterceptor():HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    internal fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor {
                chain ->
                val request = chain.request().newBuilder().addHeader(
                    "Connection",
                    "close"
                ).build()
                chain.proceed(request)
            }
            .connectTimeout(REQUEST_TIMEOUT.toLong(),TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(),TimeUnit.SECONDS)
            .build()
        return (okHttpClient)!!
    }

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://cexup.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAppApiService(retrofit: Retrofit): AppApiServices {
        return retrofit.create(AppApiServices::class.java)
    }
    @Provides
    fun provideDeviceApiService(retrofit: Retrofit): DeviceApiService {
        return retrofit.create(DeviceApiService::class.java)
    }
}
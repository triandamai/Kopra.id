package com.trian.data.di

import com.trian.data.remote.app.AppApiServices
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
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */


@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
object NetworkModule {
    const val BASE_URL_DEVICE = "http://192.168.100.154:8000/api/"
    const val BASE_URL_WEB= "http://localhost:8000/api/"

    private val REQUEST_TIMEOUT = 10
    private var okHttpClient: OkHttpClient? = null

    @Provides
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    internal fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader(
                    "Connection",
                    "close"
                ).addHeader(
                    "X-Api-Key",
                    "SECRET"
                ).addHeader(
                    "Content-Type",
                    "application/json"
                )
                .addHeader(
                    "Accept",
                    "application/json"
                )
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
        return (okHttpClient)!!
    }

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val base_url = System.getenv("BASE_URL") ?: BASE_URL_DEVICE
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAppApiService(retrofit: Retrofit): AppApiServices {
        return retrofit.create(AppApiServices::class.java)
    }

}
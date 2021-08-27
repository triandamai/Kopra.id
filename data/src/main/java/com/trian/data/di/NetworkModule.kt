package com.trian.data.di

import com.google.gson.Gson
import com.trian.data.remote.KtorEngine
import com.trian.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*

@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
object NetworkModule {
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource(KtorEngine().ktorHttpClientMock())
    }


}
package com.trian.data.di

import com.trian.data.coroutines.DefaultDispatcherProvider
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.room.CexupDatabase
import com.trian.data.remote.RemoteDataSource
import com.trian.data.repository.CexupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*


@Module
@InstallIn(SingletonComponent::class,ActivityComponent::class)
object DataModule {
    @Provides
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()


    @Provides
    fun provideRepository(
        dispatcherProvider: DispatcherProvider,
        remoteDataSource: RemoteDataSource,
        database: CexupDatabase
    ):CexupRepository{
        return CexupRepository(dispatcherProvider,database,remoteDataSource)
    }
}
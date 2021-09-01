package com.trian.data.di


import com.google.gson.Gson
import com.trian.data.coroutines.DefaultDispatcherProvider
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.room.CexupDatabase
import com.trian.data.remote.app.AppApiServices
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.remote.app.IAppRemoteDataSource
import com.trian.data.repository.CexupRepository
import com.trian.data.repository.ICexupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */


@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
object DataModule {
    @Provides
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    internal fun provideGson() = Gson()

    @Provides
    fun provideAppRemoteDataSource(appApiServices: AppApiServices): IAppRemoteDataSource {
        return AppRemoteDataSource(appApiServices)
    }


    @Provides
    fun provideRepository(
        dispatcherProvider: DispatcherProvider,
        appRemoteDataSource: IAppRemoteDataSource,
        database: CexupDatabase
    ): ICexupRepository {
        return CexupRepository(dispatcherProvider, database, appRemoteDataSource)
    }
}
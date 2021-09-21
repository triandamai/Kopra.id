package com.trian.data.di


import com.trian.data.coroutines.DefaultDispatcherProvider
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.room.CexupDatabase
import com.trian.data.local.room.MeasurementDao
import com.trian.data.local.room.NurseDao
import com.trian.data.local.room.UserDao
import com.trian.data.remote.app.AppApiServices
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.remote.app.IAppRemoteDataSource
import com.trian.data.repository.MeasurementRepositoryImpl
import com.trian.data.repository.IMeasurementRepository
import com.trian.data.repository.IUserRepository
import com.trian.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */


@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
object DataModule {
    @Provides
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()


    @Provides
    fun provideAppRemoteDataSource(appApiServices: AppApiServices): IAppRemoteDataSource {
        return AppRemoteDataSource(appApiServices)
    }


    @Provides
    fun provideMeasurementRepository(
        dispatcherProvider: DispatcherProvider,
        appRemoteDataSource: IAppRemoteDataSource,
        measurementDao: MeasurementDao
    ): IMeasurementRepository {
        return MeasurementRepositoryImpl(dispatcherProvider,measurementDao,appRemoteDataSource)
    }

    @Provides
    fun provideUserRepository(
        dispatcherProvider: DispatcherProvider,
        appRemoteDataSource: IAppRemoteDataSource,
        userDao: UserDao,
        nurseDao: NurseDao
    ): IUserRepository {
        return UserRepositoryImpl(dispatcherProvider,userDao,nurseDao,appRemoteDataSource)
    }
}
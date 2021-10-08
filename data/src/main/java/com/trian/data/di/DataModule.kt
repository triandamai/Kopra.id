package com.trian.data.di


import com.trian.data.coroutines.DefaultDispatcherProvider
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.local.room.MeasurementDao
import com.trian.data.local.room.NurseDao
import com.trian.data.local.room.UserDao
import com.trian.data.remote.app.AppApiServices
import com.trian.data.remote.app.AppRemoteDataSourceImpl
import com.trian.data.remote.app.AppRemoteDataSource
import com.trian.data.repository.*
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
    fun provideAppRemoteDataSource(appApiServices: AppApiServices): AppRemoteDataSource {
        return AppRemoteDataSourceImpl(appApiServices)
    }



    @Provides
    fun provideMeasurementRepository(
        dispatcherProvider: DispatcherProvider,
        appRemoteDataSource: AppRemoteDataSource,
        measurementDao: MeasurementDao
    ): MeasurementRepository {
        return MeasurementRepositoryImpl(dispatcherProvider,measurementDao,appRemoteDataSource)
    }

    @Provides
    fun provideUserRepository(
        dispatcherProvider: DispatcherProvider,
        appRemoteDataSource: AppRemoteDataSource,
        userDao: UserDao,
        nurseDao: NurseDao,
        persistence: Persistence
    ): UserRepository {
        return UserRepositoryImpl(dispatcherProvider,userDao,nurseDao,persistence,appRemoteDataSource)
    }

    @Provides
    fun provideDoctorRepository(
        dispatcherProvider: DispatcherProvider,
        appRemoteDataSource: AppRemoteDataSource,
    ): DoctorRepository{
        return DoctorRepositoryImpl(appRemoteDataSource = appRemoteDataSource,)
    }

    @Provides
    fun provideArticleRepository(
        appRemoteDataSource: AppRemoteDataSource,
    ):ArticleRepository{
        return ArticleRepositoryImpl(appRemoteDataSource)
    }
}
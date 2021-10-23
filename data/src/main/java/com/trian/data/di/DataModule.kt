package com.trian.data.di


import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.trian.data.coroutines.DefaultDispatcherProvider
import com.trian.data.coroutines.DispatcherProvider
import com.trian.data.local.Persistence
import com.trian.data.remote.BaseRepository
import com.trian.data.repository.UserRepository
import com.trian.data.repository.UserRepositoryImpl
import com.trian.domain.models.User

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 22/10/2021
 */
@Module
@InstallIn(SingletonComponent::class, ActivityComponent::class)
object DataModule {
    @Provides
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    internal fun provideSharePreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("fcab4de", Context.MODE_PRIVATE)
    }

    @Provides
    internal fun providePersistence(
        sharedPreferences: SharedPreferences
    ): Persistence = Persistence(
        sharedPreferences
    )


}
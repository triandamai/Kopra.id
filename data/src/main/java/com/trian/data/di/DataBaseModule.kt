package com.trian.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.trian.data.local.Persistence
import com.trian.data.local.room.CexupDatabase
import com.trian.data.local.room.CexupDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */


@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    //for test

    //production
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext:Context):CexupDatabase = Room.databaseBuilder(
        appContext,
        CexupDatabase::class.java,DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .build()


    @Provides
    internal fun provideSharePreferences(@ApplicationContext appContext: Context):SharedPreferences{
        return appContext.getSharedPreferences("fcab4de",Context.MODE_PRIVATE)
    }

    @Provides
    internal fun providePersistence(
        sharedPreferences: SharedPreferences,
    ):Persistence= Persistence(sharedPreferences)
}
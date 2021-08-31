package com.trian.data.di

import android.content.Context
import androidx.room.Room
import com.trian.data.local.room.CexupDatabase
import com.trian.data.local.room.CexupDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


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
}
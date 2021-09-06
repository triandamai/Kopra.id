package com.trian.common.utils.di

import android.content.Context
import com.google.gson.Gson
import com.trian.common.utils.utils.PermissionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class,ActivityComponent::class)
object CommonModule {
    @Provides
    fun provideGson():Gson{
        return Gson()
    }

    @Provides
    fun providePermissionUtils(@ApplicationContext appContext:Context):PermissionUtils{
        return PermissionUtils(appContext)
    }
}
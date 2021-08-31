package com.trian.continu_temperature.di

import android.content.Context
import com.wuadam.blelibrary.BleLibrary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object WinbebeModule {

    @Provides
    internal fun provideBle(@ApplicationContext appContext:Context):BleLibrary{
        return BleLibrary.getInstance(appContext)
    }
}
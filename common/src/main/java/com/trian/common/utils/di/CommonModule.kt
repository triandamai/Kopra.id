package com.trian.common.utils.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.trian.common.utils.connection.NetworkConnection
import com.trian.common.utils.utils.PermissionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 13/10/2021
 **/

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

    @Provides
    fun provideConnectionManager(@ApplicationContext appContext: Context):NetworkConnection{
        return NetworkConnection(appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }
}
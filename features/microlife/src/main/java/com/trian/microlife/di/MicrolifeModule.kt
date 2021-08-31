package com.trian.microlife.di

import android.app.Activity
import android.content.Context
import com.ideabus.model.protocol.BPMProtocol
import com.ideabus.model.protocol.ThermoProtocol
import com.trian.common.utils.sdk.SDKConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object MicrolifeModule {
    @Provides
    internal fun provideBPMProtocol(@ActivityContext appContext:Context):BPMProtocol{
        return BPMProtocol.getInstance(appContext as Activity,false,false,SDKConstant.SDK_ID_BPM)
    }

    @Provides
    internal fun provideThermoprotocol(@ActivityContext appContext: Context):ThermoProtocol{
        return ThermoProtocol.getInstance(appContext as Activity,false,false,SDKConstant.SDK_ID_THERMO)
    }
}
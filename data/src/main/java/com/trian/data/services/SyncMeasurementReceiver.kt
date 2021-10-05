package com.trian.data.services

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 04/10/2021
 */
interface SyncNotify{
    fun onNewData()
}
class SyncMeasurementReceiver(
    handler: Handler?,
    private val syncNotify:SyncNotify
    ) :ResultReceiver(handler){

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        syncNotify.onNewData()
    }
}
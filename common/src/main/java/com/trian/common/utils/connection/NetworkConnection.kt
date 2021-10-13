package com.trian.common.utils.connection

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
/**
 * Main Activity
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 13/10/2021
 **/

class NetworkConnection(
    private val connectivityManager: ConnectivityManager
    ): LiveData<Boolean>() {

    private val networkCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP) object :ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }


    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        val  builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(),networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }


}
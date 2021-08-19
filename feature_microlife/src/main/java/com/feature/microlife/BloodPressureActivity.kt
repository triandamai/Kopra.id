package com.feature.microlife

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import com.cexup_sdk.utils.DeviceUtils
import com.feature.microlife.ui.theme.CexupMobileAppTheme
import com.ideabus.model.bluetooth.MyBluetoothLE
import com.ideabus.model.data.*
import com.ideabus.model.protocol.BPMProtocol

class BloodPressureActivity : ComponentActivity() {
    private lateinit var bpmProtocol:BPMProtocol
    companion object {
        var isAttach = false
    }

    override fun onStart() {
        super.onStart()

        isAttach = true
    }

    override fun onResume() {
        super.onResume()
        bpmProtocol.let {
            startScan()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CexupMobileAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Text("Android")
                }
            }
        }
        initParam()
    }

    override fun onDestroy() {
        super.onDestroy()
        bpmProtocol.let {
            if(it.isConnected){
                bpmProtocol.disconnect()
                bpmProtocol.stopScan()
            }
        }
        isAttach = false
    }
    fun initParam(){
        bpmProtocol = BPMProtocol.getInstance(this,false,false, DeviceUtils.SDK_ID_BPM)
        bpmProtocol.setOnConnectStateListener(connectState)
        bpmProtocol.setOnDataResponseListener(dataListener)
        bpmProtocol.setOnWriteStateListener { b, s ->  }
        bpmProtocol.setOnNotifyStateListener {  }
        Log.e("init","param")
    }

    fun startScan(){
        if(isAttach){
            if(bpmProtocol.isSupportBluetooth(this)){
                bpmProtocol.startScan(10)
            }
        }
    }

    private val connectState = object :BPMProtocol.OnConnectStateListener{
        override fun onBtStateChanged(p0: Boolean) {
            TODO("Not yet implemented")
        }

        override fun onScanResult(mac: String?, name: String?, rssi: Int) {
            Log.e("Cek",mac!!)
        }

        override fun onConnectionState(p0: BPMProtocol.ConnectState?) {
            TODO("Not yet implemented")
        }

    }
    private val dataListener = object :BPMProtocol.OnDataResponseListener{
        override fun onResponseReadHistory(p0: DRecord?) {
            TODO("Not yet implemented")
        }

        override fun onResponseClearHistory(p0: Boolean) {
            TODO("Not yet implemented")
        }

        override fun onResponseReadUserAndVersionData(p0: User?, p1: VersionData?) {
            TODO("Not yet implemented")
        }

        override fun onResponseWriteUser(p0: Boolean) {
            TODO("Not yet implemented")
        }

        override fun onResponseReadLastData(
            p0: CurrentAndMData?,
            p1: Int,
            p2: Int,
            p3: Int,
            p4: Boolean
        ) {
            TODO("Not yet implemented")
        }

        override fun onResponseClearLastData(p0: Boolean) {
            TODO("Not yet implemented")
        }

        override fun onResponseReadDeviceInfo(p0: DeviceInfo?) {
            TODO("Not yet implemented")
        }

        override fun onResponseReadDeviceTime(p0: DeviceInfo?) {
            TODO("Not yet implemented")
        }

        override fun onResponseWriteDeviceTime(p0: Boolean) {
            TODO("Not yet implemented")
        }
    }
}

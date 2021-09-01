package com.trian.microlife

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ideabus.model.data.ThermoMeasureData
import com.ideabus.model.protocol.ThermoProtocol
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.microlife.viewmodel.MicrolifeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ThermometerActivity : ComponentActivity() {
    private val viewModel: MicrolifeViewModel by viewModels()
    @Inject lateinit var thermoProtocol: ThermoProtocol

    override fun onStart() {
        super.onStart()
        iniListenerThermo()
        startScan()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting2("Android")
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(thermoProtocol.isConnected) thermoProtocol.disconnect()
        thermoProtocol.stopScan()
    }

    fun startScan(){
        if(thermoProtocol.isSupportBluetooth(this)){
            Toast.makeText(this,"this device doesn't support",Toast.LENGTH_LONG).show()
        }
        thermoProtocol.startScan(6)

    }
    fun iniListenerThermo(){
        thermoProtocol.setOnConnectStateListener(object :ThermoProtocol.OnConnectStateListener{
            override fun onBtStateChanged(isEnabled: Boolean) {
                Toast.makeText(this@ThermometerActivity,"BLE is $isEnabled",Toast.LENGTH_LONG).show()
            }

            override fun onScanResult(mac: String?, name: String?, rssi: Int) {
                Log.e("STATE","${mac} ${name} ${rssi}")
            }

            override fun onConnectionState(connectionState: ThermoProtocol.ConnectState?) {
               Log.e("STATE","${connectionState?.name}")
            }
        })
        thermoProtocol.setOnNotifyStateListener {

        }
        thermoProtocol.setOnWriteStateListener { b, s ->  }
        thermoProtocol.setOnDataResponseListener(object :ThermoProtocol.OnDataResponseListener{
            override fun onResponseDeviceInfo(p0: String?, p1: Int, p2: Float) {
                TODO("Not yet implemented")
            }

            override fun onResponseUploadMeasureData(p0: ThermoMeasureData?) {
                TODO("Not yet implemented")
            }
        })
    }

}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TesMultiModuleTheme() {
        Greeting2("Android")
    }
}
package com.trian.microlife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ideabus.model.data.ThermoMeasureData
import com.ideabus.model.protocol.ThermoProtocol
import com.trian.component.ui.theme.TesMultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ThermometerActivity : ComponentActivity() {
    @Inject lateinit var thermoProtocol: ThermoProtocol
    override fun onStart() {
        super.onStart()
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

    fun iniListenerThermo(){
        thermoProtocol.setOnConnectStateListener(object :ThermoProtocol.OnConnectStateListener{
            override fun onBtStateChanged(p0: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onScanResult(p0: String?, p1: String?, p2: Int) {
                TODO("Not yet implemented")
            }

            override fun onConnectionState(p0: ThermoProtocol.ConnectState?) {
                TODO("Not yet implemented")
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
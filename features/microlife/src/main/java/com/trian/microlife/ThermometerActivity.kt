package com.trian.microlife

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.ideabus.model.data.ThermoMeasureData
import com.ideabus.model.protocol.ThermoProtocol
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.data.local.Peristence
import com.trian.domain.models.Devices
import com.trian.microlife.ui.ThermometerUi
import com.trian.microlife.viewmodel.MicrolifeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ThermometerActivity : ComponentActivity() {
    companion object{
        const val key_mac_thermometer = "microlife_temp"
        var isConnecting = false
    }
    private val viewModel: MicrolifeViewModel by viewModels()
    @Inject lateinit var thermoProtocol: ThermoProtocol
    @Inject lateinit var peristence: Peristence
    @Inject lateinit var gson: Gson

    override fun onStart() {
        super.onStart()

    }
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    ScreenTemperatureMicrolife(viewModel)
//                }
                ThermometerUi(viewModel)

            }
        }
        iniListenerThermo()
        peristence.getItemString(key_mac_thermometer)?.let {
           val device:Devices = gson.fromJson(it,Devices::class.java)
           startConnect(device)
        }
        startScan()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(thermoProtocol.isConnected) thermoProtocol.disconnect()
        thermoProtocol.stopScan()
    }
    /**
     * Scanning available device nearby app
     * before scanning should check if this app support ble and already connecting
     *
    * */

    private fun startScan(){
        //check sopprt
        if(!thermoProtocol.isSupportBluetooth(this)){
            Toast.makeText(this,"this device doesn't support",Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this,"scanning",Toast.LENGTH_LONG).show()
       if(!isConnecting) thermoProtocol.startScan(6)

    }
    /**
     * Begin Connecting to device
     * before connect should check if already connecting or in scanning state
     *
     * */
    private fun startConnect(devices: Devices){
        if(thermoProtocol.isScanning) thermoProtocol.stopScan()
        if(!isConnecting) {
            isConnecting = true
            peristence.setItem(key_mac_thermometer,gson.toJson(devices))
            thermoProtocol.connect(devices.mac)
        }
    }
    /**
     *
     * */
    private fun iniListenerThermo(){
        thermoProtocol.setOnConnectStateListener(object :ThermoProtocol.OnConnectStateListener{
            override fun onBtStateChanged(isEnabled: Boolean) {
                Toast.makeText(this@ThermometerActivity,"BLE is $isEnabled",Toast.LENGTH_LONG).show()
            }

            override fun onScanResult(mac: String?, name: String?, rssi: Int) {
                Log.e("STATE","${mac} ${name} ${rssi}")
                viewModel.addDevice(Devices(name = name!!,mac = mac!!,rssi = rssi))
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
fun ScreenTemperatureMicrolife(viewModel: MicrolifeViewModel) {
    val listDevices by viewModel.devices.observeAsState()

    LazyColumn(content = {
        items(listDevices?.size ?: 0,itemContent = {
            index: Int ->
            val device = listDevices?.get(index)
            device?.let { 
                Text(text = it.mac)
            }
        })
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TesMultiModuleTheme() {
    }
}
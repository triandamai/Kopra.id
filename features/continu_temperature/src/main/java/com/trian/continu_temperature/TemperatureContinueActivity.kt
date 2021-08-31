package com.trian.continu_temperature

import android.bluetooth.BluetoothGattCharacteristic
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.continu_temperature.viewmodel.TemperatureContinueViewModel
import com.wuadam.blelibrary.BleLibrary
import com.wuadam.blelibrary.BleLibraryListener
import com.wuadam.blelibrary.Device
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TemperatureContinueActivity : ComponentActivity() {

    private val vm:TemperatureContinueViewModel by viewModels()
    @Inject lateinit var bleLibrary: BleLibrary


    override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun initListenerWinbebe(){
        bleLibrary.addListener(object :BleLibraryListener{
            override fun onScanStart() {
                TODO("Not yet implemented")
            }

            override fun onScanStop() {
                TODO("Not yet implemented")
            }

            override fun onScan(p0: Device?) {
                TODO("Not yet implemented")
            }

            override fun onConnect(p0: Device?, p1: Exception?) {
                TODO("Not yet implemented")
            }

            override fun onReady(p0: Device?) {
                TODO("Not yet implemented")
            }

            override fun onDisconnect(p0: Device?) {
                TODO("Not yet implemented")
            }

            override fun onConnecting(p0: Device?) {
                TODO("Not yet implemented")
            }

            override fun onRetryConnecting(p0: Device?) {
                TODO("Not yet implemented")
            }

            override fun onDisConnecting(p0: Device?) {
                TODO("Not yet implemented")
            }

            override fun onDisConnectByAccident(p0: Device?) {
                TODO("Not yet implemented")
            }

            override fun onReConnectAfterAccident(p0: Device?) {
                TODO("Not yet implemented")
            }

            override fun onRssi(p0: Device?, p1: Int) {
                TODO("Not yet implemented")
            }

            override fun onBattery(p0: Device?, p1: Int) {
                TODO("Not yet implemented")
            }

            override fun onData(
                p0: Device?,
                p1: ByteArray?,
                p2: UUID?,
                p3: BluetoothGattCharacteristic?
            ) {
                TODO("Not yet implemented")
            }

            override fun onWrite(
                p0: Device?,
                p1: ByteArray?,
                p2: UUID?,
                p3: BluetoothGattCharacteristic?
            ) {
                TODO("Not yet implemented")
            }

            override fun On() {
                TODO("Not yet implemented")
            }

            override fun Off() {
                TODO("Not yet implemented")
            }

            override fun TurningOn() {
                TODO("Not yet implemented")
            }

            override fun TurningOff() {
                TODO("Not yet implemented")
            }
        })
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TesMultiModuleTheme {
        Greeting("Android")
    }
}
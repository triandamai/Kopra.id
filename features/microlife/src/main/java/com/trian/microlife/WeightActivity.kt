package com.trian.microlife

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ideabus.model.data.EBodyMeasureData
import com.ideabus.model.protocol.EBodyProtocol
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.microlife.viewmodel.MicrolifeViewModel
import javax.inject.Inject


class WeightActivity : ComponentActivity() {

    private val viewModel: MicrolifeViewModel by viewModels()
    @Inject lateinit var eBodyProtocol: EBodyProtocol

    override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting3("Android")
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
    fun initListenerEbody(){
        eBodyProtocol.setOnConnectStateListener(object :EBodyProtocol.OnConnectStateListener{
            override fun onScanResult(p0: BluetoothDevice?) {
                TODO("Not yet implemented")
            }

            override fun onConnectionState(p0: EBodyProtocol.ConnectState?) {
                TODO("Not yet implemented")
            }
        })
        eBodyProtocol.setOnDataResponseListener(object :EBodyProtocol.OnDataResponseListener{
            override fun onUserInfoUpdateSuccess() {
                TODO("Not yet implemented")
            }

            override fun onDeleteAllUsersSuccess() {
                TODO("Not yet implemented")
            }

            override fun onResponseMeasureResult2(p0: EBodyMeasureData?, p1: Float) {
                TODO("Not yet implemented")
            }

        })
    }
}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    TesMultiModuleTheme {
        Greeting3("Android")
    }
}
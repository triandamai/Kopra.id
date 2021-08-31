package com.trian.microlife


import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ideabus.model.data.*
import com.ideabus.model.protocol.BPMProtocol
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.microlife.viewmodel.MicrolifeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BloodPressureActivity : AppCompatActivity() {

    private val viewModel: MicrolifeViewModel by viewModels()

    @Inject lateinit var bpmProtocol: BPMProtocol

    override fun onStart() {
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    Greeting(viewModel)
                }
            }
        }
        Log.e("INIT BPM","$bpmProtocol")
        initListenerBPM()

    }

    override fun onResume() {
        super.onResume()
    }
    override fun onDestroy() {
        super.onDestroy()
        if(bpmProtocol.isConnected) bpmProtocol.disconnect()
        bpmProtocol.stopScan()
    }

    fun initListenerBPM(){
        bpmProtocol.setOnConnectStateListener(object :BPMProtocol.OnConnectStateListener{
            override fun onBtStateChanged(p0: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onScanResult(p0: String?, p1: String?, p2: Int) {
                TODO("Not yet implemented")
            }

            override fun onConnectionState(p0: BPMProtocol.ConnectState?) {
                TODO("Not yet implemented")
            }

        })
        bpmProtocol.setOnNotifyStateListener { TODO("Not yet implemented") }
        bpmProtocol.setOnWriteStateListener { b, s ->  }
        bpmProtocol.setOnDataResponseListener(object:BPMProtocol.OnDataResponseListener{
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
        })
    }


}

@Composable
fun Greeting(viewModels: MicrolifeViewModel = viewModel()) {
    val example = viewModels.nameLiveData.observeAsState()
    val status = viewModels.statusLiveData.observeAsState()
    Column {
        example.value?.let { Text(text = "Hello ${it}!") }
        status.value?.let {
            Text(text = "Status ${it}!")
        }
        Button(onClick = {
            viewModels.users()

        }) {
            Text(text = "Klik")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TesMultiModuleTheme {
        //Greeting("Android")
    }
}
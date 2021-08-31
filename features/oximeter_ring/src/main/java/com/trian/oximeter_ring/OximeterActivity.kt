package com.trian.oximeter_ring

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.oximeter_ring.utils.Constant
import com.xtremeprog.sdk.ble.BleService


class OximeterActivity : ComponentActivity() {
    override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting4("Android")
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

    fun startServiceSPo2(){
        val binIntent = Intent(this,BleService::class.java)
        bindService(binIntent,mConnectionService,Context.BIND_AUTO_CREATE)

        Constant.initDir(this)
    }

    fun stopServiceSpo2(){
        unbindService(mConnectionService)
    }

    private val mConnectionService = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            TODO("Not yet implemented")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }
    }
}

@Composable
fun Greeting4(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    TesMultiModuleTheme {
        Greeting4("Android")
    }
}
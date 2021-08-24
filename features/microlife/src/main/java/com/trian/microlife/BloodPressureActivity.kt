package com.trian.microlife

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trian.common.utils.network.NetworkStatus


import com.trian.microlife.ui.theme.TesMultiModuleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BloodPressureActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    Greeting(viewModel)
                }
            }
        }

    }
}

@Composable
fun Greeting(viewModels: UserViewModel = viewModel()) {
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
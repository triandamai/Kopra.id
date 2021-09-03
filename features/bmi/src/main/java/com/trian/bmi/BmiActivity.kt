package com.trian.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardSmarthWatch
import com.trian.component.ui.theme.TesMultiModuleTheme


class BmiActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesMultiModuleTheme {
                AppBarFeature(
                    name = "Naky",
                    image = "image",
                    onBackPressed = { /*TODO*/ },
                    onProfil = {})
            }
        }
    }
}


@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    TesMultiModuleTheme {
        CardSmarthWatch(
            param = "Blood Oxygen",
            imageParam = "img",
            vlastest = "98",
            vmax = "99",
            vmin = "90",
            satuan = "%",
            onClickCard = {}
        )
    }
}



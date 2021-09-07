package com.trian.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.trian.component.cards.CardFeatures
import com.trian.component.ui.theme.*


class BmiActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {

                TesMultiModuleTheme{
                    CardFeatures(image = "", name = "Body Mask Index", onClickPressed = {})
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
    }
}





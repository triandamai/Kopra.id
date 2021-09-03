package com.trian.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardSmarthWatch

import com.trian.component.ui.theme.*
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
        CardDetailSmartWatch()
    }
}

@Composable
fun CardDetailSmartWatch(){
    Box(modifier = Modifier
        .background(ColorBackground)
        .fillMaxSize()){
        Column {

            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(200.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(12.dp))
            {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "120",
                                color = ColorFontSw,
                                fontSize = 72.sp
                            )

                        }

                    }
                    Column() {
                        
                    }
                    Column() {
                        
                    }

                }

            }


        }
    }
}




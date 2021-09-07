package com.trian.microlife.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.appbar.AppBarFeature
import com.trian.component.ui.theme.CardColor
import com.trian.component.ui.theme.ColorBackground
import com.trian.component.ui.theme.TesMultiModuleTheme


@Preview
@Composable
fun ThermometerUiPreview(){
    TesMultiModuleTheme {
        ThermometerUi()
    }

}

@Composable
fun ThermometerUi(){
    Column(
        modifier = Modifier
            .background(ColorBackground)
            .fillMaxSize()
    ) {
        AppBarFeature(name = "Temerature", image = "", onBackPressed = { /*TODO*/ }, onProfil = {})

        Card(
            modifier = Modifier
                .width(200.dp)
                .padding(10.dp)
                .background(color = CardColor),
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
        ) {

        }
        Card(
            modifier = Modifier
                .width(200.dp)
                .padding(10.dp)
                .background(color = CardColor),
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
        ) {

        }
        Card(
            modifier = Modifier
                .width(200.dp)
                .padding(10.dp)
                .background(color = CardColor),
            shape = RoundedCornerShape(12.dp),
            elevation = 4.dp,
        ) {

        }
    }

}

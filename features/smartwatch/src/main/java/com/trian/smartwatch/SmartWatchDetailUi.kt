package com.trian.smartwatch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarFeature
import com.trian.component.cards.CardDetailSmartWatchUi
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.smartwatch.viewmodel.SmartWatchViewModel
import compose.icons.Octicons
import compose.icons.octicons.Calendar24
import kotlinx.coroutines.CoroutineScope

@Composable
fun DetailSmartWatchUi(
    modifier:Modifier=Modifier,
    viewModel: SmartWatchViewModel,
    nav:NavHostController,
    scope:CoroutineScope,
    page:String,
    onClickCalender: ()-> Unit
){
    val listMeasurement by viewModel.listRespiration
    Scaffold(
        topBar = {
            AppBarFeature(name = "Andi", image ="" , onBackPressed = { /*TODO*/ }, onProfil = {})
        },
        backgroundColor = Color.White
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            //calender
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            ) {
                Icon(
                    Octicons.Calendar24,
                    contentDescription = "",
                    tint = ColorFontFeatures,
                    modifier = modifier.clickable { onClickCalender() }
                )
                Text(
                    text = "Mon, Sep 14",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = ColorFontFeatures,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

            }
            //latest value
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "122", //systole
                    fontSize = 32.sp,
                    color = ColorFontFeatures
                )
                Spacer(modifier = modifier.width(2.dp))
                Text(
                    text = "/",
                    fontSize = 32.sp,
                    color = ColorFontFeatures
                )
                Spacer(modifier = modifier.width(2.dp))
                Text(
                    text = "122", //diastole
                    fontSize = 32.sp,
                    color = ColorFontFeatures
                )
                Spacer(modifier = modifier.width(5.dp))
                Text(
                    text = "mmHg",
                    fontSize = 16.sp,
                    color = ColorFontFeatures,
                    modifier = modifier.padding(top = 10.dp)
                )
            }
            CardDetailSmartWatchUi(
                type = page,
                onCalenderClick = {},
                list = listOf()
            )
        }

    }
}

@Preview
@Composable
fun DetailSmartWatchUiPreview(){
    TesMultiModuleTheme {
        DetailSmartWatchUi(
            onClickCalender = {},
            page="Bpm",
            viewModel = viewModel(),
            scope = rememberCoroutineScope(),
            nav = rememberNavController()
        )
    }
}
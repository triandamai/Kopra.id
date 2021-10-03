package com.trian.component.appbar

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
/**
 * AppBar List Order
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 03/10/2021
 */
@Composable
fun APpBarListOrder(
    shouldFloating:Boolean
){
    TopAppBar(
        title = {
            Text(
                text = "Call Doctor",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        backgroundColor = Color.White,
        elevation = if(shouldFloating){
            0.dp
        }else{
            3.dp
        }
    )
}
package com.trian.component.cards

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

/**
 * Main Page
 * Author Trian damai
 * Created by Trian Damai
 * 04/11/2021
 */

@Composable
fun HeaderDashboard(
    modifier: Modifier=Modifier,
    displayName:String=""
){
    Column(modifier = modifier.fillMaxWidth().background(Color.Transparent)) {
        Text(
            text = "Halo",
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            text = displayName,
            style = TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewHeaderDashboardLight(){
    HeaderDashboard(displayName="Trian Damai")
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewHeaderDashboardDark(){
    HeaderDashboard(displayName="Trian Damai")
}
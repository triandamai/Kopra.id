package com.trian.component.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
    Column {
        Text(
            text = "Halo"
        )
        Text(text = displayName)
    }
}

@Preview
@Composable
fun PreviewHeaderDashboardLight(){
    HeaderDashboard(displayName="Trian Damai")
}

@Preview
@Composable
fun PreviewHeaderDashboardDark(){
    HeaderDashboard(displayName="Trian Damai")
}
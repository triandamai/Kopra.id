package com.trian.module.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24
import compose.icons.octicons.KebabHorizontal24

@Composable
fun PageKuisioner(m:Modifier = Modifier){
    Scaffold(
        topBar = {
            TopBarSection()
        }
    ) {

    }
}

@Composable
private fun TopBarSection(m:Modifier = Modifier){
    Column() {
        Row(modifier = m.fillMaxWidth()){
            Card(
                shape = RoundedCornerShape(8.dp)
            ){
                Icon(
                    Octicons.ChevronLeft24,
                    contentDescription = "",
                    modifier = m.padding(5.dp)
                )
            }
            Text(text = "Questionnaire")
            Card(
                shape = RoundedCornerShape(8.dp)
            ){
                Icon(
                    Octicons.KebabHorizontal24,
                    contentDescription = "",
                    modifier = m.padding(5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPageKuesioner(){
    PageKuisioner()
}
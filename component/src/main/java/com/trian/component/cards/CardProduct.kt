package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.R
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color

@Composable
fun CardProduct(m: Modifier = Modifier){
    Card(){
        Box() {
            Image(painter = painterResource(id = R.drawable.doctor_dummy), contentDescription = "")
            Icon(Icons.Filled.Info,"",tint = Color.White)
        }
        Column() {
            Row() {

            }
            Text(text = "")
            Row() {

            }
            Row() {
                
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardProduct(){
    CardProduct()
}
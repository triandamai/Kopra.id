package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardArticle(title: String, subtitle:String,caption:String){
    Card (shape = RoundedCornerShape(topEndPercent = 30)){
        Box(modifier = Modifier.height(200.dp)){
            Image(painter = painterResource(id = ), contentDescription = )
        }
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = title)
            Text(text= subtitle)
            Text(text = caption,color= Color.White)
        }
    }
}

@Preview
@Composable
fun PreviewCardArticle(){
    CardArticle("3-Month","Pilates Beginner","By Sarah Allen")
}
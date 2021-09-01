package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.R

@Composable
fun CardArticle(title: String, subtitle:String,caption:String){
    Card (
        shape = RoundedCornerShape(topEndPercent = 30),

    ){
        Box{
            Image(painter = painterResource(id = R.drawable.doctor_dummy) ,contentDescription = "tes")
        }
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = title,color= Color.White)
            Text(text= subtitle,color= Color.White)
            Text(text = caption,color= Color.White)
        }
    }
}

@Preview
@Composable
fun PreviewCardArticle(){
    CardArticle("3-Month","Pilates Beginner","By Sarah Allen")
}
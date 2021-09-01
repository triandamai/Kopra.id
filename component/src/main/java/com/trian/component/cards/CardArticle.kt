package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.*

@Composable
fun CardArticle(title: String, subtitle:String,caption:String){
    Card (
        shape = RoundedCornerShape(topEndPercent = 30),
    ){
        Box(modifier = Modifier
            .background(
                color = Color.Black
            )
            .paint(
                painter = painterResource(
                    id = R.drawable.doctor_dummy
                )
            )
        )
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(text = title,color= Color.White,fontSize = 20.sp)
            Text(text= subtitle,color= Color.White,fontSize = 20.sp)
            Text(text = caption,color= Color.White,fontWeight = FontWeight.Light,fontSize = 10.sp)
        }
    }
}

@Preview
@Composable
fun CardArticleLear(modifier: Modifier=Modifier){
   Card(
       shape = RoundedCornerShape(
           topEnd = 10.dp,
           topStart = 10.dp
       ),
   ) {
       Column() {
           Image(
               painter = painterResource(id = R.drawable.doctor_dummy),
               contentDescription = "image",
               contentScale = ContentScale.Crop,
               modifier = Modifier.fillMaxWidth()
           )
           Row(
               modifier = modifier.padding(12.dp).fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
           ){
               Text(
                   text = "MEDIA",
                   fontSize = 11.sp,
                   fontWeight = FontWeight.Normal,
                   color = Color(0xFF6E798C))
               Text(
                   text = "17 days ago",
                   fontSize = 11.sp,
                   fontWeight = FontWeight.Normal,
                   color = Color(0xFF6E798C)
               )
           }
           Text(
               text = "SMM starter pack, part 2: content promotion",
               fontSize = 20.sp,
               fontWeight = FontWeight.Bold,
               color = Color(0xFF6E798C),
               modifier = modifier.padding(horizontal = 12.dp)
           )
       }
   }
}

@Preview
@Composable
fun PreviewCardArticle(){
    CardArticle("3-Month","Pilates Beginner","By Sarah Allen")
}
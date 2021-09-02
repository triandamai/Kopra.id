package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R

@Composable
fun CardDoctor(m:Modifier=Modifier,category:String,nameDoctor:String){
    Box(modifier = m
        .width(150.dp)
        .height(210.dp)
        .background(color = Color.Black)
    ){
        Image(
            painter = painterResource(
                id = R.drawable.doctor_dummy),
            alpha = 0.8f,
            contentDescription = "",
            contentScale =
            ContentScale.Crop,
            modifier =
            m
                .fillMaxSize())
        Column(modifier = m
            .align(Alignment.BottomStart)
            .padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(Icons.Filled.Android,"",tint = Color.White,modifier = m.width(15.dp))
                Spacer(modifier = m.width(5.dp))
                Text(text = category,color = Color.White,fontSize = 10.sp)
            }
            Text(
                text = nameDoctor,
                color = Color.White,
                fontSize = 20.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
fun PreviewCardDoctor(){
    CardDoctor(category = stringResource(R.string.datadummycategory),nameDoctor = stringResource(R.string.datadummynamedoctor))
}


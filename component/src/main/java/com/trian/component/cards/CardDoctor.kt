package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.domain.models.Doctor
import com.trian.domain.models.HospitalList
import com.trian.domain.models.OnlineSchedule

/**
 * `Card Doctor
 * Author PT Cexup Telemedhicine
 * Created by Kholid Barat Daya
 * 04/09/2021
 */
@Composable
fun CardDoctor(m:Modifier=Modifier,doctor:Doctor,onClick:(doctor:Doctor,index:Int)->Unit){
    Card(shape = RoundedCornerShape(percent = 5),) {
        Box(modifier = m
            .width(150.dp)
            .height(210.dp)
            .background(color = Color.Black),
        ){
            Image(
                painter = painterResource(
                    id = R.drawable.dummy_doctor),
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
                    Text(text = doctor.speciality,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light)
                }
                Text(
                    text = doctor.title,
                    color = Color.White,
                    fontSize = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardDoctor(){
    val onlineSchedule = OnlineSchedule(monday = "13:00-14:00",tuesday = "13:00-14:00",wednesday = "13:00-14:00")
    val offlineSchedule = OnlineSchedule(monday = "13:00-14:00",tuesday = "13:00-14:00",wednesday = "13:00-14:00")
    CardDoctor(
        onClick = {doctor, index ->  },
        doctor = Doctor(speciality = "Kandungan",onlineSchedule = onlineSchedule,offlineSchedule = offlineSchedule,hospitalList = listOf(),hospital = "Cexup",description = "",slug = "",thumb = "",thumbOriginal = "",title = "Dr. Yakob Simatupang" )
    )
}


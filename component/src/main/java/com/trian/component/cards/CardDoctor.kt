package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
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
import com.trian.component.ui.theme.LightBackground
import com.trian.domain.models.Doctor
import com.trian.domain.models.Hospital
import com.trian.domain.models.HospitalList
import com.trian.domain.models.Schedule
import compose.icons.Octicons
import compose.icons.octicons.Clock16

/**
 * `Card Doctor
 * Author PT Cexup Telemedhicine
 * Created by Kholid Barat Daya
 * 04/09/2021
 */
@Composable
fun CardDoctor(m:Modifier=Modifier,doctor:Doctor,onClick:(doctor:Doctor,index:Int)->Unit){
    Card(
        modifier = m
            .width(155.dp)
            .height(200.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable { onClick(doctor, 0) },
        shape = RoundedCornerShape(percent = 5),
    ) {
        Box(modifier = m
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
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
fun CardDoctorHospital(
    doctor: Doctor,
    hospital: Hospital,
    index:Int,
    onClick: (doctor: Doctor, index: Int) -> Unit,
    m: Modifier = Modifier
){
    Card(
        modifier = m
            .fillMaxWidth()
            .background(LightBackground)
            .clickable { onClick(doctor,index)}
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = m
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.dummy_doctor),
                alpha = 0.8f,
                contentDescription = "",
                contentScale =
                ContentScale.Crop,
                modifier = m
                    .size(90.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                )
            Spacer(modifier = m.width(8.dp))
            Column(
                modifier = m
                    .fillMaxWidth(),
            ) {
                Text(
                    text = doctor.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = doctor.speciality,
                    fontSize = 14.sp,
                    color = Color.LightGray.copy(0.9f),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = hospital.name,
                    fontSize = 13.sp,
                    color = Color.LightGray.copy(0.9f),
                )
                Row(
                    modifier = m
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Octicons.Clock16,
                        contentDescription = "",
                        tint = Color.LightGray.copy(0.9f),
                        modifier = m.size(13.dp)
                    )
                    Spacer(modifier = m.width(3.dp))
                   Text(
                        text = doctor.online_schedule?.monday.toString(),
                        fontSize = 13.sp,
                        color = Color.LightGray.copy(0.9f),
                    )

                }

            }
        }

    }

}

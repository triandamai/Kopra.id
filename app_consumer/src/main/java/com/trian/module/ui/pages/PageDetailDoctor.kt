package com.trian.module.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.Doctor
import com.trian.domain.models.HospitalList
import com.trian.domain.models.Schedule
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.*


@Composable
fun PageDetailDoctor(m:Modifier = Modifier,nav : NavHostController){
    val scrollState = rememberScrollState()
    val onlineSchedule = Schedule(monday = "13:00-14:00",tuesday = "13:00-14:00",wednesday = "13:00-14:00")
    val offlineSchedule = Schedule(monday = "13:00-14:00",tuesday = "13:00-14:00",wednesday = "13:00-14:00")
    Scaffold(
        topBar = {
                 Row(
                     horizontalArrangement = Arrangement.SpaceBetween,
                     modifier = m
                         .fillMaxWidth()
                         .padding(5.dp),
                     verticalAlignment = Alignment.CenterVertically
                 ){
                     Card(shape = CircleShape){
                         Icon(
                             Octicons.ChevronLeft24,
                             contentDescription = "",
                             modifier = m.padding(5.dp)
                         )
                     }
                     Card(shape = CircleShape){
                         Icon(
                             Octicons.KebabHorizontal24,
                             contentDescription = "",
                             modifier = m.padding(5.dp)
                         )
                     }
                 }
        },
        bottomBar = {
    ComponentBottomSection(
        doctor = Doctor(
            speciality = "Kandungan",
            online_schedule = onlineSchedule,
            offline_schedule = offlineSchedule,
            hospital_list = listOf<HospitalList>(
                HospitalList(
                    name = "",
                    id = 1,
                    online_price = "20000",
                    offline_price = "2000"
                ),
            ),
            hospital = "Cexup",description = "",slug = "",thumb = "",thumb_original = "",title = "Dr. Yakob Simatupang" ),
        nav = nav
    )
    },
        backgroundColor = LightBackground
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = m
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            ComponentTopDetailDoctor(
                doctor = Doctor(speciality = "Kandungan",online_schedule = onlineSchedule,offline_schedule = offlineSchedule,hospital_list = listOf(),hospital = "Cexup",description = "",slug = "",thumb = "",thumb_original = "",title = "Dr. Yakob Simatupang" )
            )
            BodySection(
                doctor = Doctor(
                    speciality = "Kandungan",
                    online_schedule = onlineSchedule,
                    offline_schedule = offlineSchedule,
                    hospital_list = listOf(),
                    hospital = "Cexup",
                    description = "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. ",
                    slug = "",thumb = "",thumb_original = "",
                    title = "Dr. Yakob Simatupang"
                )
            )
        }
    }
}

@Composable
private fun ComponentTopDetailDoctor(m: Modifier=Modifier,doctor:Doctor){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = m
            .background(color = LightBackground)
            .padding(bottom = 10.dp, end = 10.dp, start = 10.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(bottomEnd = 10.dp,))
    ){
        Image(
            painter = painterResource(id = R.drawable.dummy_doctor),
            contentDescription = "",
            modifier = m
                .clip(
                    shape = RoundedCornerShape(10)
                )
                .height(height = 120.dp)
                .width(width = 120.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = m.height(10.dp))
        Text(
            text = doctor.title,
            style = MaterialTheme.typography.h1.copy(
                fontSize = 18.sp,
                letterSpacing = 0.1.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            text = doctor.speciality,
            style = MaterialTheme.typography.h1.copy(
                fontSize = 18.sp,
                letterSpacing = 1.sp
            )
        )
        Spacer(modifier = m.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Box(modifier= m
                .clip(CircleShape)
                .height(40.dp)
                .width(40.dp)
                .background(color = BluePrimary.copy(alpha = 0.2f))
            ){
                Icon(Octicons.Megaphone24,
                    contentDescription = "",
                    modifier = m
                        .padding(5.dp)
                        .align(
                            alignment = Alignment.Center
                        ),
//                    tint = Color.White
                )
            }
            Spacer(modifier = m.width(10.dp))
            Box(modifier= m
                .clip(CircleShape)
                .height(40.dp)
                .width(40.dp)
                .background(color = BluePrimary.copy(alpha = 0.2f))
            ){
                Icon(Octicons.CommentDiscussion24,
                    contentDescription = "",
                    modifier = m.padding(5.dp),
//                    tint = Color.White
                )
            }
        }

    }
}

@Composable
private fun BodySection(m: Modifier=Modifier,doctor:Doctor){
    val minggu:String = doctor.online_schedule?.let { it.sunday?.let { "Sunday" }?: run { "" } }.toString()
    val senin:String = doctor.online_schedule?.let { it.monday?.let { "Monday" }?: run { "" } }.toString()
    val selasa:String = doctor.online_schedule?.let { it.tuesday?.let { "Tuesday" }?: run { "" } }.toString()
    val rabu:String = doctor.online_schedule?.let { it.wednesday?.let { "Wednesday" }?: run { "" } }.toString()
    val kamis:String = doctor.online_schedule?.let { it.thursday?.let { "Thursday" }?: run { "" } }.toString()
    val jumat:String = doctor.online_schedule?.let { it.friday?.let { "Friday" }?: run { "" } }.toString()
    val sabtu:String = doctor.online_schedule?.let { it.saturday?.let { "Saturday" }?: run { "" } }.toString()
    val kumpulanHari:Any = "${senin} ${selasa} ${rabu} ${kamis} ${jumat} ${sabtu} ${minggu}"


    Card(
        modifier = m
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(topEnd = 20.dp,topStart = 20.dp)
    ) {
        Column() {
            Column(modifier = m.padding(10.dp)) {
                Text(text = "Biography",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.1.sp
                    )
                )
                Spacer(modifier = m.height(15.dp))
                Text(
                    text = doctor.description.capitalizeWords(),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 15.sp,
                        letterSpacing = 0.1.sp,
                        color = Color.Gray
                    )
                )
                Spacer(modifier = m.height(15.dp))
                Text(
                    text = "Online Schedule",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 0.1.sp
                    )
                )
                Spacer(
                    modifier = m.height(15.dp)
                )
                Text(
                    text = "Mon - Fri, Morning 8 AM - Night 8 PM",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 15.sp,
                        letterSpacing = 0.1.sp,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}

@Composable
private fun ComponentBottomSection(
    m:Modifier = Modifier,
       doctor:Doctor,
       nav : NavHostController
){
    Card(modifier = m
        .fillMaxWidth()
        .background(color = Color.White)
        .coloredShadow(
            color = ColorFontFeatures,
            alpha = 0.1f,
        )
    ) {
        Column(
            modifier = m
                .fillMaxWidth()
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = m
                    .fillMaxWidth()
                    .padding(10.dp)) {
                Text(text = "Consultation price",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 13.sp,
                    letterSpacing = 0.1.sp,
                    fontWeight = FontWeight.Bold,
                ))
                Text(text = "IDR ${doctor.hospital_list[0].online_price}",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 13.sp,
                        letterSpacing = 0.1.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = m.height(10.dp))
            Button(
                onClick = { nav.navigate(Routes.SHEET_FORM_ORDER) },
                modifier = m
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary)
            ) {
                Text(
                    text = "Book Appointment",
                    modifier = m.padding(8.dp),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 15.sp,
                        letterSpacing = 0.1.sp,
                        color = Color.White,
                    )
                )
            }
        }

    }
}
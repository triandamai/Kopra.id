package com.trian.module.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.Doctor
import com.trian.domain.models.Hospital
import com.trian.domain.models.HospitalList
import com.trian.domain.models.Schedule
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.*


@Composable
fun PageDetailDoctor(m:Modifier = Modifier){
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
            onlineSchedule = onlineSchedule,
            offlineSchedule = offlineSchedule,
            hospitalList = listOf<HospitalList>(
                HospitalList(
                    name = "",
                    id = 1,
                    onlinePrice = "20000",
                    offlinePrice = "2000"
                ),
            ),
            hospital = "Cexup",description = "",slug = "",thumb = "",thumbOriginal = "",title = "Dr. Yakob Simatupang" )
    )
    },
        backgroundColor = LightBackground
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ComponentTopDetailDoctor(
                doctor = Doctor(speciality = "Kandungan",onlineSchedule = onlineSchedule,offlineSchedule = offlineSchedule,hospitalList = listOf(),hospital = "Cexup",description = "",slug = "",thumb = "",thumbOriginal = "",title = "Dr. Yakob Simatupang" )
            )
            BodySection()
        }
    }
}

@Composable
private fun ComponentTopDetailDoctor(m: Modifier=Modifier,doctor:Doctor){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = m
            .background(color = LightBackground)
            .padding(bottom = 10.dp,end = 10.dp,start = 10.dp)
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
                fontSize = 12.sp,
                letterSpacing = 0.1.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            text = doctor.speciality,
            style = MaterialTheme.typography.h1.copy(
                fontSize = 12.sp,
                letterSpacing = 1.sp
            )
        )
        Spacer(modifier = m.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Box(modifier= m
                .clip(CircleShape)
                .height(20.dp)
                .width(20.dp)
                .background(color = BluePrimary.copy(alpha = 0.2f))
            ){
                Icon(Octicons.Megaphone24,
                    contentDescription = "",
                    modifier = m.padding(5.dp),
//                    tint = Color.White
                )
            }
            Spacer(modifier = m.width(5.dp))
            Box(modifier= m
                .clip(CircleShape)
                .height(20.dp)
                .width(20.dp)
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
private fun BodySection(m: Modifier=Modifier){
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
                Spacer(modifier = m.height(5.dp))
                Text(
                    text = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final copy is available.",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 15.sp,
                        letterSpacing = 0.1.sp
                    )
                )
            }
//            Column(modifier = m.padding(10.dp)) {
//                Row(verticalAlignment = Alignment.CenterVertically){
//                    Text(text = "Reviews",
//                        style = MaterialTheme.typography.h1.copy(
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 18.sp,
//                            letterSpacing = 0.1.sp
//                        )
//                    )
//                    Spacer(modifier = m.width(5.dp))
//                    Icon(
//                        Octicons.StarFill24,
//                        contentDescription = "",
//                        tint = Color.Yellow,
//                        modifier = m.height(15.dp)
//                    )
//                    Spacer(modifier = m.width(2.dp))
//                    Text(text = "4.9",
//                        style = MaterialTheme.typography.h1.copy(
//                            fontWeight = FontWeight.Medium,
//                            fontSize = 12.sp,
//                            letterSpacing = 0.1.sp
//                        )
//                    )
//                    Spacer(modifier = m.width(2.dp))
//                    Text(text = "(123)",
//                        style = MaterialTheme.typography.h1.copy(
//                            fontSize = 12.sp,
//                            letterSpacing = 0.1.sp,
//                            color = ColorGray
//                        ),
//                    )
//                }
//            }
            CardReview()
        }
    }
}

@Composable
private fun ComponentBottomSection(m:Modifier = Modifier,doctor:Doctor){
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
                Text(text = "IDR ${doctor.hospitalList[0].onlinePrice}",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 13.sp,
                        letterSpacing = 0.1.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = m.height(10.dp))
            Button(
                onClick = {  },
                modifier = m
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Book Appointment",modifier = m.padding(8.dp))
            }
        }

    }
}

@Composable
private fun CardReview(m: Modifier=Modifier){
       Card(
           modifier = m
               .coloredShadow(color = ColorFontFeatures, alpha = 0.1f)
               .padding(10.dp),
           shape = RoundedCornerShape(10.dp)
       ){
           Column(modifier = m.padding(10.dp)) {
               Row(
                   verticalAlignment = Alignment.Top,
                   horizontalArrangement = Arrangement.SpaceBetween,
                   modifier = m
                       .fillMaxWidth()
                       .padding(top = 10.dp, end = 10.dp, start = 10.dp,)
               ){
                   Row{
                       Image(
                           painter = painterResource(id = R.drawable.dummy_doctor),
                           contentDescription = "",
                           modifier = m
                               .clip(shape = CircleShape)
                               .width(50.dp)
                               .height(50.dp),
                           contentScale = ContentScale.Crop
                       )
                       Spacer(modifier = m.width(10.dp))
                       Column(horizontalAlignment = Alignment.Start) {
                           Text(text = "Nur Kholid",
                               style = MaterialTheme.typography.h1.copy(
                                   fontSize = 18.sp,
                                   fontWeight = FontWeight.Medium,
                                   letterSpacing = 0.1.sp
                               )
                           )
                           Text(text = "1 Day Ago",
                               style = MaterialTheme.typography.h1.copy(
                                   fontSize = 15.sp,
                                   color = ColorGray
                               )
                           )
                       }
                   }
                   Row(verticalAlignment = Alignment.CenterVertically){
                       Icon(
                           Octicons.StarFill24,
                           contentDescription = "",
                           tint = Color.Yellow,
                           modifier = m.height(15.dp)
                       )
                       Spacer(modifier = m.width(2.dp))
                       Text(text = "4.9",
                           style = MaterialTheme.typography.h1.copy(
                               fontWeight = FontWeight.Medium,
                               fontSize = 12.sp,
                               letterSpacing = 0.1.sp
                           )
                       )
                   }
               }
               Text(
                   text = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final copy is available.",
                   modifier = m
                       .padding(10.dp),
                   style = MaterialTheme.typography.h1.copy(
                       fontSize = 15.sp,
                       fontWeight = FontWeight.Normal,
                       letterSpacing = 0.1.sp
                   ),
                   maxLines = 2,
                   overflow = TextOverflow.Ellipsis
               )
           }
       }
}

@Preview
@Composable
fun PreviewPageDetailDoctor(){
    PageDetailDoctor()
}
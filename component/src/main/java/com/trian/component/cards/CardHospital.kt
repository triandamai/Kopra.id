package com.trian.component.cards

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.*
import com.trian.domain.models.Hospital
import compose.icons.Octicons
import compose.icons.octicons.Location16
import compose.icons.octicons.Location24

@Composable
fun CardHospital(m:Modifier=Modifier,hospital:Hospital,onClick:(hospital: Hospital, index:Int)->Unit){
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = m
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = m.clickable { onClick(hospital,1) }
        ) {
            Box(modifier = m
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .padding(5.dp)) {
                Box(modifier = m
                    .background(
                        color = Color.Black.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(5.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_cexup),
                        contentDescription = "",
                        modifier = m
                            .height(80.dp)
                            .width(80.dp),
                    )
                }
            }
            Spacer(modifier = m.width(5.dp))
            Column(modifier = m.padding(10.dp)) {
                Text(text = hospital.name,color = Color.Black,
                    style = MaterialTheme.typography.h1.copy(fontSize = 18.sp,fontWeight = FontWeight.W400))
                Text(text = hospital.address,color = ColorGray,
                    style = MaterialTheme.typography.h1.copy(fontSize = 15.sp,fontWeight = FontWeight.W400),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }
    }

}

@Composable
fun HospitalCard(
    hospitalPict : Painter,
    contentDescription : String,
    modifier: Modifier = Modifier,
    hospital:Hospital,onClick:(hospital: Hospital, index:Int)->Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(hospital, 1) },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(120.dp)) {

            Image(
                painter = hospitalPict,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        BlackOpacity
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                BlueOpacity,
                            ),
                            startY = 140f,
                            tileMode = TileMode.Clamp
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
            ) {
                Column() {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                       Image(
                           painter = painterResource(id = R.drawable.logo_cexup),
                           contentDescription = "",
                           modifier = Modifier
                               .size(65.dp)
                               .padding(8.dp),
                       )
                    }
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = hospital.name,
                            style = TextStyle(color = Color.White, fontSize = 14.sp),
                            textAlign = TextAlign.Center,

                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Octicons.Location16,
                                contentDescription = "location",
                                modifier = Modifier.size(13.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = hospital.address,
                                style = TextStyle(color = Color.White, fontSize = 12.sp),
                            )
                        }
                    }

                }

            }

        }

    }
}

@Composable
fun CardHospital2(
    hospitalPict: Painter,
    index:Int=0,
    hospital: Hospital,
    onClick:(hospital: Hospital, index:Int)->Unit,
    m:Modifier = Modifier
){
    Card(
        modifier = m
            .fillMaxWidth()
            .padding(top = 4.dp,bottom = 4.dp)
            .clickable { onClick(hospital, 1) },
        elevation = 0.dp
    ) {
        Column(
            modifier = m
                .fillMaxWidth()
                .padding(
                    top = when (index) {
                        0 -> 16.dp
                        else -> 8.dp
                    }, bottom = 8.dp, start = 16.dp, end = 16.dp
                )
                .background(Color.White.copy(alpha = 0.8f))
        ) {
            Box(modifier = Modifier.height(120.dp)) {
                Image(
                    painter = hospitalPict,
                    contentDescription = "Picture hospital",
                    contentScale = ContentScale.Crop,
                    modifier = m.clip(shape = RoundedCornerShape(5.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_cexup),
                        contentDescription = "Logo Hospital",
                        modifier = Modifier
                            .size(90.dp)
                            .padding(8.dp),
                    )
                }
            }
            Spacer(modifier = m.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = hospital.name,
                    style = TextStyle(color = Color.Black.copy(0.9f), fontSize = 16.sp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold

                )
                Spacer(modifier = m.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = m.fillMaxWidth()
                ) {
                    Icon(
                        Octicons.Location24,
                        contentDescription = "location",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Blue
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = hospital.address,
                        style = TextStyle(color = Color.DarkGray, fontSize = 14.sp),
                        modifier = m.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = m.height(10.dp))
        }

    }

}

@Preview
@Composable
fun PreviewCardHospital(){
    TesMultiModuleTheme() {
       CardHospital2(hospital =
       Hospital(
            id = 1,
            slug = "rs-tele-cexup",
            description = "",
            thumb = "",
            thumbOriginal = "",
            name = "RS Tele Cexup",
            address = "Jl. Jakarta Barat RT005/003, Meruya",
            others = ""),
       hospitalPict = painterResource(id = R.drawable.hospital),
       onClick = {hospital, index ->  })
    }
}
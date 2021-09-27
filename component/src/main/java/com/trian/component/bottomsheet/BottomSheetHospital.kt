package com.trian.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.domain.models.Hospital
import compose.icons.Octicons
import compose.icons.octicons.Clock16
import compose.icons.octicons.DeviceMobile16
import compose.icons.octicons.Location16
import compose.icons.octicons.Megaphone16

@Composable
fun BottomSheetHospital(
    m:Modifier = Modifier,
    HospitalLogo: Painter,
    hospital: Hospital
){
    Box(
        m
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))

    ){
        Column(
            modifier = m
                .background(LightBackground)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = m
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(5.dp),
            ) {

                    Image(
                        painter = HospitalLogo,
                        contentDescription = "logo",
                        modifier = m
                            .size(90.dp)
                            .clip(shape = RoundedCornerShape(5.dp))
                            .padding(5.dp)
                    )
                Spacer(modifier = m.width(5.dp))
                Text(
                    text = hospital.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = m.fillMaxWidth()
                )

            }
            Spacer(modifier = m.height(5.dp))
            Column(
                modifier = m
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.White)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = m
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Octicons.Location16,
                        contentDescription = "",
                        tint = Color.LightGray.copy(0.9f),
                        modifier = m.size(14.dp)
                    )
                    Spacer(modifier = m.width(3.dp))
                    Text(
                        text = "Address :",
                        fontSize = 14.sp,
                        color = Color.LightGray.copy(0.9f),
                    )

                }
                Spacer(modifier = m.height(2.dp))
                Text(
                    text = hospital.address,
                    fontSize = 14.sp,
                )

            }
            Spacer(modifier = m.height(5.dp))
            Column(
                modifier = m
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.White)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = m
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Octicons.Clock16,
                        contentDescription = "",
                        tint = Color.LightGray.copy(0.9f),
                        modifier = m.size(14.dp)
                    )
                    Spacer(modifier = m.width(3.dp))
                    Text(
                        text = "Time :",
                        fontSize = 14.sp,
                        color = Color.LightGray.copy(0.9f),
                    )

                }
                Spacer(modifier = m.height(2.dp))
                Text(
                    text = "Monday - Friday: from 09.30 AM to 11.59 PM",
                    fontSize = 14.sp
                )
                Text(
                    text = "Saturday - Sunday: Close",
                    fontSize = 14.sp
                )

            }
            Spacer(
                modifier = m.height(5.dp)
            )
            Column(
                modifier = m
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.White)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = m
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Octicons.DeviceMobile16,
                        contentDescription = "",
                        tint = Color.LightGray.copy(0.9f),
                        modifier = m.size(14.dp)
                    )
                    Spacer(modifier = m.width(3.dp))
                    Text(
                        text = "Phone :",
                        fontSize = 14.sp,
                        color = Color.LightGray.copy(0.9f),
                    )

                }
                Spacer(modifier = m.height(2.dp))
                Text(
                    text = "202-555-0195",
                    fontSize = 14.sp
                )

            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomSheetHospital(){
    TesMultiModuleTheme {
        BottomSheetHospital(HospitalLogo = painterResource(id = R.drawable.logo_cexup),hospital = Hospital(
            id = 1,
            slug = "rs-tele-cexup",
            description = "",
            thumb = "",
            thumbOriginal = "",
            name = "RS Tele Cexup",
            address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
            others = "",
        ),)
    }
}
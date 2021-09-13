package com.trian.component.cards

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.*
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.Doctor
import com.trian.domain.models.Order
import compose.icons.Octicons
import compose.icons.octicons.Calendar16
import compose.icons.octicons.Clock16
import compose.icons.octicons.Dot24
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter


@Composable
fun CardOrder(modifier:Modifier=Modifier,order:Order,index:Int,onClick: (index: Int) -> Unit){
    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density
    Column(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                )
        ) {
            Row(
                modifier= modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Dr. Yakob Togar",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = modifier
                        .height(10.dp))
                    Text(text = "Obgyn")
                }
                Image(
                    painter = painterResource(id = R.drawable.dummy_profile),
                    modifier = modifier
                        .height(50.dp)
                        .width(50.dp)
                        .clip(CircleShape),
                    contentScale= ContentScale.FillWidth,
                    contentDescription = "picture doctor"
                )

            }
            Divider()
            Row(
                modifier= modifier
                    .fillMaxWidth()
                    .padding(
                        top = 26.dp,
                        bottom = 4.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Octicons.Calendar16, contentDescription = "Time")
                    Spacer(modifier = modifier.width(6.dp))
                    Text(text = "10.30 AM")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Octicons.Clock16, contentDescription = "Time")
                    Spacer(modifier = modifier.width(6.dp))
                    Text(text = "10.30 AM")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Octicons.Dot24, contentDescription = "Time")
                    Spacer(modifier = modifier.width(6.dp))
                    Text(text = "Confirmed")
                }
            }
            Row(
                modifier= modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(
                    modifier=modifier
                        .width(currentWidth/2 - 40.dp),
                    colors=ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray,
                    ),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Cancel",color=Color.Gray)
                }
                Button(
                    modifier=modifier
                        .width(currentWidth/2 - 40.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Start Meeting")
                }
            }
        }

    }
}

@Preview
@Composable
fun PreviewCardOrder(){
    CardOrder(order = Order(
        start = "",
        address = "",
        thumb = "",
        hospital = "RS TELE CEXUP",
        price = "20",
        speciality = "Obgyn",
        doctor = "Dr. Yakob Simatupang",
        date = "2021-08-06",
        accountName = "",
        accountNumber = "",
        allowed = true,
        bankName = "",
        deletedSchedule = true,
        doctorHospitalID = 213123,
        doctorNote = "",
        doctorSlug = "",
        estimate = "10:00 AM - 2:00 PM",
        join = true,
        note = "",
        paid = true,
        patient = "",
        patientID = 21,
        paymentToken = "",
        prescription = "",
        provisional = "",
        refund = true,
        requestAccess = true,
        requestRescheduleDoctor = false,
        requestReschedulePatient = false,
        statusOrder = 1,
        transactionID = "",
        type = "",
    ),index = 0,
        onClick = {})
}


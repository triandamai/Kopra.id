package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.ColorGray
import com.trian.domain.models.Doctor
import com.trian.domain.models.Order
import compose.icons.Octicons
import compose.icons.octicons.Clock16

@Composable
fun CardOrder(m: Modifier = Modifier, order: Order, onClick:(order: Order, index:Int)->Unit){
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = m
                .padding(
                    start = 30.dp,
                    end = 30.dp,
                    top = 10.dp,
                    bottom = 10.dp
                )
                .fillMaxWidth()
                .clickable { onClick(order,1) },
            elevation = 0.dp,
        ){
            Row(modifier = m
                .padding(5.dp)){
                Image(
                    painter = painterResource(id = R.drawable.doctor_dummy),
                    contentDescription = "",
                    modifier = m
                        .height(80.dp)
                        .width(80.dp)
                        .fillMaxSize()
                        .clip(
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentScale = ContentScale.Crop,
                )
                Column(modifier = m.padding(10.dp)) {
                    Text(
                        text = order.doctor,
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.W500,
                            fontSize = 13.sp,
                            letterSpacing = 0.5.sp
                        )
                    )
                    Text(text = order.speciality,style = MaterialTheme.typography.h1
                        .copy(
                            fontWeight = FontWeight.Light,
                            fontSize = 13.sp,
                            color = ColorGray,
                            letterSpacing = 0.5.sp))
                    Spacer(modifier = m.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Icon(
                           imageVector = Octicons.Clock16,"",
                            tint = Color.Black,modifier = m.width(15.dp))
                        Spacer(modifier = m.width(5.dp))
                        Text(text = order.estimate,
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 13.sp,
                                color = Color.Black,
                            letterSpacing = 0.5.sp))
                    }
                }
            }
        }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCardOrder(){
        Column() {
            CardOrder(
                order = Order(
                    start = "",
                    address = "",
                    thumb = "",
                    hospital = "",
                    price = "20",
                    speciality = "Obgyn",
                    doctor = "Dr. Yakob Simatupang",
                    date = "",
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
                ),
                onClick = {order, index ->  }
            )
            CardOrder(
                order = Order(
                    start = "",
                    address = "",
                    thumb = "",
                    hospital = "",
                    price = "20",
                    speciality = "Obgyn",
                    doctor = "Dr. Yakob Simatupang",
                    date = "",
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
                ),
                onClick = {order, index ->  }
            )
        }
}
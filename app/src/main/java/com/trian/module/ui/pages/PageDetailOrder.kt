package com.trian.module.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.*
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.DetailOrder
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.*

fun String.capitalizeWords(): String = split(" ").map { it.replaceFirstChar(Char::titlecase) }.joinToString(" ")

@Composable
fun PageDetailOrder(
    m:Modifier = Modifier,
    detailOrder: DetailOrder
){
    Scaffold(
        backgroundColor = LightBackground,
        topBar = {
            Row(
                modifier = m
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Card(
                    shape = RoundedCornerShape(8.dp)
                ){
                    Icon(
                        Octicons.ChevronLeft24,
                        contentDescription = "",
                        modifier = m.padding(5.dp)
                    )
                }
                Card(
                    shape = RoundedCornerShape(8.dp)
                ){
                    Icon(
                        Octicons.KebabHorizontal24,
                        contentDescription = "",
                        modifier = m.padding(5.dp)
                    )
                }
            }
        }
    ){
        Column(){
            TopSection(detailOrder = detailOrder)
            BodySection(detailOrder = detailOrder)
        }
    }
}

@Composable
private fun TopSection(m: Modifier=Modifier,detailOrder:DetailOrder){
    Row(
        modifier = m
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp)
    ){
        Column(
            modifier = m.coloredShadow(
                color = Color.Black,
            ),
        ){
            Card(
                shape = RoundedCornerShape(percent = 8),
                modifier = m
                    .height(120.dp)
                    .width(100.dp)
            ){
                Box(
                    modifier = m
                        .background(color = Color.Black),
                ){
                    Image(
                        painter = painterResource(id = R.drawable.dummy_doctor),
                        contentDescription = "",
                        alpha = 0.8f,
                        contentScale = ContentScale.Crop,
                        modifier = m.fillMaxSize()
                    )
                }
            }
        }
        Spacer(modifier = m.width(20.dp))
        Column() {
            Column() {
                Text(text = detailOrder.doctor.capitalizeWords(),
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                )
                Spacer(modifier = m.height(5.dp))
                Text(
                    text = "${detailOrder.speciality.capitalizeWords()} (${detailOrder.hospital.capitalizeWords()})",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = m.height(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Card(shape = RoundedCornerShape(8.dp)){
                    Icon(
                        Octicons.StarFill24,
                        contentDescription = "",
                        tint = Color.Yellow,
                        modifier = m.padding(5.dp)
                    )
                }
                Spacer(modifier = m.width(10.dp))
                Column() {
                    Text(text = "Rating")
                    Spacer(modifier = m.height(5.dp))
                    Text(text = "4.5 out of 5")
                }
            }
        }
    }
}

@Composable
private fun BodySection(m: Modifier = Modifier,detailOrder: DetailOrder){
    val screenWidth = (LocalContext.current.resources.displayMetrics.widthPixels.dp/
            LocalDensity.current.density) / 2
    Column(
        modifier = m
            .padding(
                vertical = 20.dp,
                horizontal = 20.dp,
            )
            .fillMaxWidth()
    ){
        Column(){
            Text(text = "You're Order",style = MaterialTheme.typography.h1.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp
            ))
            Spacer(modifier = m.height(15.dp))
            Column(modifier = m.coloredShadow(
                color = BluePrimary,
                alpha = 0.1f
            )){
                Card(
                    shape = RoundedCornerShape(10),
                    modifier = m.fillMaxWidth()
                ){
                    Row(
                        modifier = m.padding(10.dp),
                    ){
                        Card(
                            shape = CircleShape,elevation = 0.dp,
                            backgroundColor = Color.Green.copy(alpha = 0.5f),
                        ){
                            Icon(Octicons.Archive24,"",
                                modifier = m
                                    .height(40.dp)
                                    .width(40.dp)
                                    .padding(10.dp),
                                tint = Color.White,
                            )
                        }
                        Spacer(modifier = m.width(10.dp))
                        Column(){
                            Text(text = "Transaction ID",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 18.sp,
                                    letterSpacing = 0.1.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Spacer(modifier = m.height(5.dp))
                            Text(text = "#${detailOrder.transactionId}",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 15.sp,
                                    letterSpacing = 0.1.sp,
                                    color = ColorGray,
                                )
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(text = "Patient Details",style = MaterialTheme.typography.h1.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp
            ))
            Spacer(modifier = m.height(15.dp))
            Column(modifier = m.coloredShadow(
                color = BluePrimary,
                alpha = 0.1f
            )){
                Card(
                    shape = RoundedCornerShape(10),
                    modifier = m.fillMaxWidth()
                ){
                    Row(
                        modifier = m.padding(10.dp),
                    ){
                        Card(
                            shape = CircleShape,elevation = 0.dp,
                            backgroundColor = Color.Gray.copy(alpha = 0.5f),
                        ){
                            Icon(Octicons.Person24,"",
                                modifier = m
                                    .height(40.dp)
                                    .width(40.dp)
                                    .padding(10.dp),
                                tint = Color.White,
                            )
                        }
                        Spacer(modifier = m.width(10.dp))
                        Column(){
                            Text(text = detailOrder.patient.capitalizeWords(),
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 18.sp,
                                    letterSpacing = 0.1.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Spacer(modifier = m.height(5.dp))
                            Text(text = detailOrder.note,
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 15.sp,
                                    letterSpacing = 0.1.sp,
                                    color = ColorGray,
                                )
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(text = "Payment",style = MaterialTheme.typography.h1.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp
            ))
            Spacer(modifier = m.height(15.dp))
            Column(modifier = m.coloredShadow(
                color = BluePrimary,
                alpha = 0.1f
            )){
                Card(
                    shape = RoundedCornerShape(10),
                    modifier = m.fillMaxWidth()
                ){
                    Row(
                        modifier = m
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row(){
                            Card(
                                shape = CircleShape,elevation = 0.dp,
                                backgroundColor = Color.Gray.copy(alpha = 0.5f),
                            ){
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_wallet), "",
                                    modifier = m
                                        .height(40.dp)
                                        .width(40.dp)
                                        .padding(10.dp),
                                    tint = Color.Unspecified
                                )
                            }
                            Spacer(modifier = m.width(15.dp))
                            Column(){
                                Text(text = "Method Payment",
                                    style = MaterialTheme.typography.h1.copy(
                                        fontSize = 15.sp,
                                        letterSpacing = 0.1.sp,
                                        color = ColorGray,
                                    )
                                )
                                Spacer(modifier = m.height(5.dp))
                                Text(text = "BCA",
                                    style = MaterialTheme.typography.h1.copy(
                                        fontSize = 18.sp,
                                        letterSpacing = 0.1.sp,
                                    )
                                )
                            }
                        }
                        Spacer(modifier = m.width(5.dp))
                        Row(){
                            Card(
                                shape = CircleShape,elevation = 0.dp,
                                backgroundColor = Color.Gray.copy(alpha = 0.5f),
                            ){
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_attach_money_24),
                                    "",
                                    modifier = m
                                        .height(40.dp)
                                        .width(40.dp)
                                        .padding(10.dp),
                                    tint = Color.Unspecified,
                                )
                            }
                            Spacer(modifier = m.width(10.dp))
                            Column(){
                                Text(text = "Charge",
                                    style = MaterialTheme.typography.h1.copy(
                                        fontSize = 15.sp,
                                        letterSpacing = 0.1.sp,
                                        color = ColorGray,
                                    )
                                )
                                Text(
                                    text = "IDR ${detailOrder.price}".split(".")[0],
                                    style = MaterialTheme.typography.h1.copy(
                                        fontSize = 18.sp,
                                        letterSpacing = 0.1.sp,
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = m.height(20.dp))
        Row(
            modifier = m.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick={},
                modifier = m.width(screenWidth - 40.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(width = 0.dp,color = Color.Unspecified,),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Gray.copy(alpha = 0.5f)
                )
            ) {
                Text(text="Cancel",
                    style = MaterialTheme.typography.h1.copy(
                        color = Color.White,
                        fontSize = 15.sp,
                    ))
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = m.width(screenWidth - 40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = GreenDark
                )
            ) {
                Text(text="Pay now",
                    style = MaterialTheme.typography.h1.copy(
                        color = Color.White,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.sp
                    ))
            }
        }
    }
}

@Composable
@Preview
private fun PreviewPageDetailOrder(){
    PageDetailOrder(
        detailOrder = DetailOrder(
            deletedSchedule=false,
            transactionId="RUI-MT613ad53a47ad0",
            hospital="Klinik 5",
            doctorHospitalId=1,
            address="Jl. Janj",
            doctor="dr. Yakob Togar",
            doctorSlug="dr-YAKOB",
    speciality="OBGYN",
    patient="nurkholid",
    patientId=1,
    note="Lorem ipsum doler bla bla bla",
    doctorNote="<p>Hello World</p>",
    prescription="<p>Hello World</p>",
    provisional="<p>Hello World</p>",
    date="2021-09-10",
    estimate= "10:49",
    type= "call",
    price="100000.00",
    requestReschedulePatient=false,
    requestRescheduleDoctor=false,
    statusOrder=9,
    paid=true,
    refund=false,
    bankName="",
    accountNumber="",
    accountName="",
    start=false,
    join=true,
    paymentToken="",
    allowed=true,
    requestAccess=false,
    paymentUrl="",
    thumb="https:\\/\\/rsui-app.cexup.com\\/storage\\/avatars\\/\\/128\\/conversions\\/Xljpc7jaj7G7VFFE-thumb.jpg",
        )
    )
}
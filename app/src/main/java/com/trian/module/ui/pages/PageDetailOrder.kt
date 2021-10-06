package com.trian.module.ui.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
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
    nav: NavHostController,
    detailOrder: DetailOrder = DetailOrder(
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
){
    val scrollState = rememberScrollState()
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
        },
        bottomBar = {
            BottomSection(nav = nav)
        }
    ){
        Column(modifier = m.verticalScroll(scrollState)){
            TopSection(detailOrder = detailOrder)
            BodySection(detailOrder = detailOrder,)
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
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                )
                Spacer(modifier = m.height(5.dp))
                Text(
                    text = "${detailOrder.speciality.lowercase().capitalizeWords()} (${detailOrder.hospital.capitalizeWords()})",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 15.sp,
                        letterSpacing = 0.1.sp,
                    )
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
                    Text(
                        text = "Rating",
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 15.sp,
                            letterSpacing = 0.1.sp,
                        )
                    )
                    Spacer(modifier = m.height(5.dp))
                    Text(
                        text = "4.5 out of 5",
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 15.sp,
                            letterSpacing = 0.1.sp,
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun BodySection(textStyle: TextStyle = TextStyle(), m: Modifier = Modifier, detailOrder: DetailOrder){
    var scaledTextStyle by remember { mutableStateOf(textStyle) }
    var readyToDraw by remember { mutableStateOf(false) }

    Column(
        modifier = m
            .padding(
                vertical = 20.dp,
                horizontal = 20.dp,
            )
            .fillMaxWidth()
    ){
        Column(){
            Text(
                text = "You're Order",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.1.sp,
                ),
                modifier = m
                    .drawWithContent {
                        if(readyToDraw){ drawContent()
                        }
                    },
                onTextLayout = {
                        textLayoutResult ->
                    if(textLayoutResult.didOverflowWidth){
                        scaledTextStyle = scaledTextStyle.copy(
                            fontSize =scaledTextStyle.fontSize*0.9,
                        )
                    }else{
                        readyToDraw = true
                    }
                }
            )
            Spacer(modifier = m.height(10.dp))
            Column(modifier = m.coloredShadow(
                color = BluePrimary,
                alpha = 0.1f
            )){
                Column(
                    modifier = m
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10),)
                        .background(color = Color.White)
                        .height(80.dp)
                ){
                    Row(
                        modifier = m.padding(10.dp),
                    ){
                        Card(
                            shape = CircleShape,elevation = 0.dp,
                            backgroundColor = GreenDark,
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_receipt_long_24), "",
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
                                    fontSize = 13.sp,
                                    letterSpacing = 0.1.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = m
                                    .drawWithContent {
                                        if(readyToDraw){ drawContent()
                                        }
                                    },
                                onTextLayout = {
                                        textLayoutResult ->
                                    if(textLayoutResult.didOverflowWidth){
                                        scaledTextStyle = scaledTextStyle.copy(
                                            fontSize =scaledTextStyle.fontSize*0.9,
                                        )
                                    }else{
                                        readyToDraw = true
                                    }
                                }
                            )
                            Spacer(modifier = m.height(10.dp))
                            Text(text = "#${detailOrder.transactionId}",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 12.sp,
                                    letterSpacing = 0.1.sp,
                                    color = ColorGray,
                                ),
                                modifier = m
                                    .drawWithContent {
                                        if(readyToDraw){ drawContent()
                                        }
                                    },
                                onTextLayout = {
                                        textLayoutResult ->
                                    if(textLayoutResult.didOverflowWidth){
                                        scaledTextStyle = scaledTextStyle.copy(
                                            fontSize =scaledTextStyle.fontSize*0.9,
                                        )
                                    }else{
                                        readyToDraw = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(
                text = "Patient Details",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.1.sp,
                ),
                modifier = m
                    .drawWithContent {
                        if(readyToDraw){ drawContent()
                        }
                    },
                onTextLayout = {
                        textLayoutResult ->
                    if(textLayoutResult.didOverflowWidth){
                        scaledTextStyle = scaledTextStyle.copy(
                            fontSize =scaledTextStyle.fontSize*0.9,
                        )
                    }else{
                        readyToDraw = true
                    }
                }
            )
            Spacer(modifier = m.height(10.dp))
            Column(modifier = m.coloredShadow(
                color = BluePrimary,
                alpha = 0.1f
            )){
                Column(
                    modifier = m.fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10),)
                        .background(color = Color.White)
                        .height(80.dp)
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
                            Text(
                                text = detailOrder.patient.capitalizeWords(),
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 13.sp,
                                    letterSpacing = 0.1.sp,
                                    fontWeight = FontWeight.Medium
                                ),
                                modifier = m
                                    .drawWithContent {
                                        if(readyToDraw){ drawContent()
                                        }
                                    },
                                onTextLayout = {
                                        textLayoutResult ->
                                    if(textLayoutResult.didOverflowWidth){
                                        scaledTextStyle = scaledTextStyle.copy(
                                            fontSize =scaledTextStyle.fontSize*0.9,
                                        )
                                    }else{
                                        readyToDraw = true
                                    }
                                }
                            )
                            Spacer(modifier = m.height(10.dp))
                            Text(
                                text = detailOrder.note,
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 12.sp,
                                    letterSpacing = 0.1.sp,
                                    color = ColorGray,
                                ),
                                modifier = m
                                    .drawWithContent {
                                        if(readyToDraw){ drawContent()
                                        }
                                    },
                                onTextLayout = {
                                        textLayoutResult ->
                                    if(textLayoutResult.didOverflowWidth){
                                        scaledTextStyle = scaledTextStyle.copy(
                                            fontSize =scaledTextStyle.fontSize*0.9,
                                        )
                                    }else{
                                        readyToDraw = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(
                text = "Payment",
                style = MaterialTheme.typography.h1.copy(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.1.sp,
            ),
                modifier = m
                    .drawWithContent {
                        if(readyToDraw){ drawContent()
                        }
                    },
                onTextLayout = {
                        textLayoutResult ->
                    if(textLayoutResult.didOverflowWidth){
                        scaledTextStyle = scaledTextStyle.copy(
                            fontSize =scaledTextStyle.fontSize*0.9,
                        )
                    }else{
                        readyToDraw = true
                    }
                }
            )
            Spacer(modifier = m.height(10.dp))
            Column(modifier = m.coloredShadow(
                color = BluePrimary,
                alpha = 0.1f
            )){
                Card(
                    modifier = m.fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10),)
                        .background(color = Color.White)
                        .height(80.dp)
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
                            Spacer(modifier = m.width(10.dp))
                            Column(){
                                Text(text = "Method Payment",
                                    style = MaterialTheme.typography.h1.copy(fontSize = 12.sp,color = Color.Gray),
                                    modifier = m
                                        .drawWithContent {
                                            if(readyToDraw){ drawContent()
                                            }
                                        },
                                    onTextLayout = {
                                            textLayoutResult ->
                                        if(textLayoutResult.didOverflowWidth){
                                            scaledTextStyle = scaledTextStyle.copy(
                                                fontSize =scaledTextStyle.fontSize*0.9,
                                            )
                                        }else{
                                            readyToDraw = true
                                        }
                                    }
                                )
                                Spacer(modifier = m.height(10.dp))
                                Text(text = "BCA",
                                    style = MaterialTheme.typography.h1.copy(
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = m
                                        .drawWithContent {
                                            if(readyToDraw){ drawContent()
                                            }
                                        },
                                    onTextLayout = {
                                            textLayoutResult ->
                                        if(textLayoutResult.didOverflowWidth){
                                            scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                                        }else{
                                            readyToDraw = true
                                        }
                                    }
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
                                    style = MaterialTheme.typography.h1.copy(fontSize = 12.sp,color = Color.Gray),
                                    modifier = m
                                        .drawWithContent {
                                            if(readyToDraw){ drawContent()
                                            }
                                        },
                                    onTextLayout = {
                                            textLayoutResult ->
                                        if(textLayoutResult.didOverflowWidth){
                                            scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                                        }else{
                                            readyToDraw = true
                                        }
                                    }
                                )
                                Spacer(modifier = m.height(10.dp))
                                Text(
                                    text = "IDR ${detailOrder.price}".split(".")[0],
                                    style = MaterialTheme.typography.h1.copy(fontSize = 12.sp,fontWeight = FontWeight.Bold),
                                    modifier = m
                                        .drawWithContent {
                                            if(readyToDraw){ drawContent()
                                            }
                                        },
                                    onTextLayout = {
                                            textLayoutResult ->
                                        if(textLayoutResult.didOverflowWidth){
                                            scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                                        }else{
                                            readyToDraw = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomSection(m:Modifier = Modifier, nav: NavHostController, ){
    Column(
        modifier = m
            .fillMaxWidth()
            .background(color = Color.White)
            .coloredShadow(color = ColorFontFeatures, alpha = 0.1f)
    ){
        Column(
            modifier = m
                .fillMaxWidth()
                .padding(10.dp),
        ){
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(8.dp),
                modifier = m.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = BluePrimary
                )
            ) {
                Text(text="Pay now",
                    style = MaterialTheme.typography.h1.copy(
                        color = Color.White,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.sp
                    ))
            }
            Spacer(modifier = m.height(20.dp))
            Button(
                onClick={nav.navigate(Routes.SHEET_CANCEL_ORDER)},
                shape = RoundedCornerShape(8.dp),
                modifier = m.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFCBCBCB)
                )
            ) {
                Text(text="Cancel",
                    style = MaterialTheme.typography.h1.copy(
                        color = Color.White,
                        fontSize = 15.sp,
                    )
                )
            }
        }
    }
}
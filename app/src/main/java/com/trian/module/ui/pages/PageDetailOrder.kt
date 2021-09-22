package com.trian.module.ui.pages

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
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.*

@Composable
fun PageDetailOrder(m:Modifier = Modifier){
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
            TopSection()
            BodySection()
        }
    }
}

@Composable
private fun TopSection(m: Modifier=Modifier){
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
                Text(text = "Dr. Yakob Simatupang",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                )
                Spacer(modifier = m.height(5.dp))
                Text(
                    text = "Obgyn (RS. Universitas Indonesia)",
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
private fun BodySection(m: Modifier = Modifier){
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
                            Text(text = "CXP-MT6142ac2d451db MT",
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
                            Text(text = "Nur Kholid Fathurohman",
                                style = MaterialTheme.typography.h1.copy(
                                    fontSize = 18.sp,
                                    letterSpacing = 0.1.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Spacer(modifier = m.height(5.dp))
                            Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived",
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
                                Spacer(modifier = m.height(5.dp))
                                Text(text = "IDR 20000",
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
    }
}

@Composable
@Preview
private fun PreviewPageDetailOrder(){
    PageDetailOrder()
}
package com.trian.module.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.cards.CardAppVersion
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.LightBackgroundAccent
import com.trian.component.ui.theme.RedOpacity
import com.trian.component.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.ArrowRight16
import compose.icons.octicons.ArrowRight24
import compose.icons.octicons.ArrowUpRight24

/**
 * Dashboard Profile
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 11/09/2021
 */

@Composable
fun PageProfile(modifier: Modifier = Modifier,listState: LazyListState){
    Scaffold(
        backgroundColor= LightBackground,
        topBar = {},
    ) {
        LazyColumn(
            state=listState,
            modifier=modifier
                .padding(horizontal = 16.dp),
            content = {
                item{
                    Row(
                        modifier = modifier
                            .padding(top = 20.dp,bottom = 20.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dummy_profile),
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.FillWidth,
                            modifier = modifier
                                .clip(CircleShape)
                                .height(80.dp)
                                .width(80.dp)
                        )
                        Spacer(modifier = modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Trian Damai",
                                style = TextStyle(fontSize = 20.sp,fontWeight = FontWeight.Bold)
                            )
                            Text(text = "tes")
                        }

                    }
                }
                item {
                    Column(
                        modifier= modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))

                    ) {
                       Column(
                           modifier
                               .fillMaxWidth()
                               .coloredShadow(
                                   color = ColorFontFeatures
                               )
                               .clip(RoundedCornerShape(10.dp))
                               .background(Color.White)) {
                           Row(modifier = modifier
                               .padding(
                                   start = 16.dp,
                                   end = 16.dp,
                                   top = 20.dp,
                                   bottom = 8.dp
                               )
                               .fillMaxWidth(),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Column {
                                   Text(
                                       text = "Display Name",
                                       color = Color.Gray
                                   )
                                   Text(
                                       text = "Trian Damai",
                                       style = TextStyle(
                                           fontSize = 18.sp,
                                           fontWeight = FontWeight.Bold
                                       )
                                   )
                               }
                               Button(onClick = { /*TODO*/ }) {
                                   Text(text = "Edit")
                               }
                           }
                           Row(modifier = modifier
                               .padding(
                                   start = 16.dp,
                                   end = 16.dp,
                                   top = 16.dp,
                                   bottom = 8.dp
                               )
                               .fillMaxWidth(),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Column {
                                   Text(
                                       text = "Email",
                                       color = Color.Gray
                                   )
                                   Text(
                                       text = "triandamai@gmail.com",
                                       style = TextStyle(
                                           fontSize = 18.sp,
                                           fontWeight = FontWeight.Bold
                                       ))
                               }
                               Button(onClick = { /*TODO*/ }) {
                                   Text(text = "Edit")
                               }
                           }
                           Row(modifier = modifier
                               .padding(
                                   start = 16.dp,
                                   end = 16.dp,
                                   top = 16.dp,
                                   bottom = 20.dp
                               )
                               .fillMaxWidth(),
                               verticalAlignment = Alignment.CenterVertically,
                               horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                               Column {
                                   Text(
                                       text = "Phone",
                                       color = Color.Gray
                                   )
                                   Text(
                                       text = "+628122xxx",
                                       style = TextStyle(
                                           fontSize = 18.sp,
                                           fontWeight = FontWeight.Bold
                                       ))
                               }
                               Button(onClick = { /*TODO*/ }) {
                                   Text(text = "Change")
                               }
                           }
                       }
                    }
                }
                item {
                    Column(
                        modifier= modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))

                    ) {
                        Column(
                            modifier
                                .fillMaxWidth()
                                .coloredShadow(
                                    color = ColorFontFeatures
                                )
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)) {
                            Row(modifier = modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 20.dp,
                                    bottom = 8.dp
                                )
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {

                                    Text(
                                        text = "Device Setting",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                            Row(modifier = modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 16.dp,
                                    bottom = 8.dp
                                )
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {

                                    Text(
                                        text = "About App",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        ))
                                }

                            }
                            Row(modifier = modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 16.dp,
                                    bottom = 20.dp
                                )
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {

                                    Text(
                                        text = "+FAQ",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        ))
                                }

                            }
                        }
                    }
                }
                item {
                    Column(
                        modifier= modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))
                    ) {

                        Column(modifier = modifier
                            .fillMaxWidth()
                            .coloredShadow(
                                color = ColorFontFeatures
                            )
                            .background(Color.White),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(modifier=modifier
                                .padding(horizontal = 16.dp, vertical = 16.dp)) {
                                Text(text = "History")
                                Text(text = "Teleconsultation")
                            }
                            Spacer(modifier = modifier.height(10.dp))
                            Column(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .background(LightBackgroundAccent)
                            ) {
                                Spacer(modifier = modifier.height(16.dp))
                                Row(
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(text = "See all history",
                                    color = Color.Gray)
                                    Spacer(modifier = modifier
                                        .width(16.dp))
                                    Icon(
                                        imageVector = Octicons.ArrowRight16,
                                        tint= Color.Gray,
                                        contentDescription = "See all")

                                }
                                Spacer(modifier = modifier.height(16.dp))
                            }
                        }

                    }
                }
                item {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(
                                text = "Sign Out",
                                style=TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = Color.Red
                            )
                        }
                    }
                }
                item {
                    CardAppVersion()
                }
                item {
                    Spacer(modifier =modifier.height(80.dp))
                }
        })
    }
}

@Preview
@Composable
fun PreviewPageProfile(){
    PageProfile(listState = rememberLazyListState())
}
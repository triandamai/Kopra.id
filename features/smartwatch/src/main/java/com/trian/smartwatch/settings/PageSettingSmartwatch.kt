package com.trian.smartwatch.settings


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.appbar.AppBarDetail
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.component.utils.coloredShadow
import com.trian.smartwatch.R


@Composable
fun PageSettingSw(
    m:Modifier = Modifier
){
    Scaffold(
        topBar = {AppBarDetail(page = "SmartWatch Settings", onBackPressed = {})}
    ) {
        LazyColumn(
            modifier = m
                .fillMaxWidth()
                .fillMaxHeight()
            ) {

            item {
                //theme
                Column(
                    modifier = m
                        .fillMaxWidth()
                        .padding(16.dp)
                        .coloredShadow(color = Color.DarkGray)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp))

                ) {
                    Column(
                        modifier = m
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = m
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Choose your theme",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )
                        }
                        Spacer(modifier = m.height(10.dp))
                        Text(
                            text = "Select theme to make your smarthwatch awesome ",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = m.height(10.dp))
                        LazyRow(
                            modifier = m
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
                        ){
                            item {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(modifier = m
                                        .background(
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(10.dp)
                                        .height(120.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.theme1),
                                            contentDescription = "",
                                            modifier = m
                                                .clip(shape = RoundedCornerShape(15.dp))
                                                .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                        )
                                        Column(
                                            modifier = m.padding(start = 5.dp, top = 5.dp)
                                        ) {
                                            Row(
                                                modifier = m
                                                    .background(Color.White, shape = CircleShape),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                RadioButton(
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = Color.Black,
                                                        unselectedColor = Color.Black,
                                                        disabledColor = Color.Black
                                                    ),
                                                )
                                            }
                                        }

                                    }
                                    Text(text = "Default")
                                }
                            }
                            item {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(modifier = m
                                        .background(
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(10.dp)
                                        .height(120.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.theme2),
                                            contentDescription = "",
                                            modifier = m
                                                .clip(shape = RoundedCornerShape(15.dp))
                                                .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                        )
                                        Column(
                                            modifier = m.padding(start = 5.dp, top = 5.dp)
                                        ) {
                                            Row(
                                                modifier = m
                                                    .background(Color.White, shape = CircleShape),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                RadioButton(
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = Color.Black,
                                                        unselectedColor = Color.Black,
                                                        disabledColor = Color.Black
                                                    ),
                                                )
                                            }
                                        }

                                    }
                                    Text(text = "Theme 1")
                                }
                            }
                            item {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(modifier = m
                                        .background(
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(10.dp)
                                        .height(120.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.theme3),
                                            contentDescription = "",
                                            modifier = m
                                                .clip(shape = RoundedCornerShape(15.dp))
                                                .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                        )
                                        Column(
                                            modifier = m.padding(start = 5.dp, top = 5.dp)
                                        ) {
                                            Row(
                                                modifier = m
                                                    .background(Color.White, shape = CircleShape),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                RadioButton(
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = Color.Black,
                                                        unselectedColor = Color.Black,
                                                        disabledColor = Color.Black
                                                    ),
                                                )
                                            }
                                        }

                                    }
                                    Text(text = "Theme 2")
                                }
                            }
                            item {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(modifier = m
                                        .background(
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(10.dp)
                                        .height(120.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.theme4),
                                            contentDescription = "",
                                            modifier = m
                                                .clip(shape = RoundedCornerShape(15.dp))
                                                .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                        )
                                        Column(
                                            modifier = m.padding(start = 5.dp, top = 5.dp)
                                        ) {
                                            Row(
                                                modifier = m
                                                    .background(Color.White, shape = CircleShape),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                RadioButton(
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = Color.Black,
                                                        unselectedColor = Color.Black,
                                                        disabledColor = Color.Black
                                                    ),
                                                )
                                            }
                                        }

                                    }
                                    Text(text = "Theme 3")
                                }
                            }
                            item {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(modifier = m
                                        .background(
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(10.dp)
                                        .height(120.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.theme5),
                                            contentDescription = "",
                                            modifier = m
                                                .clip(shape = RoundedCornerShape(15.dp))
                                                .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                        )
                                        Column(
                                            modifier = m.padding(start = 5.dp, top = 5.dp)
                                        ) {
                                            Row(
                                                modifier = m
                                                    .background(Color.White, shape = CircleShape),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                RadioButton(
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = Color.Black,
                                                        unselectedColor = Color.Black,
                                                        disabledColor = Color.Black
                                                    ),
                                                )
                                            }
                                        }

                                    }
                                    Text(text = "Theme 4")
                                }
                            }
                            item {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(modifier = m
                                        .background(
                                            color = Color.Transparent,
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(10.dp)
                                        .height(120.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.theme6),
                                            contentDescription = "",
                                            modifier = m
                                                .clip(shape = RoundedCornerShape(15.dp))
                                                .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                                        )
                                        Column(
                                            modifier = m.padding(start = 5.dp, top = 5.dp)
                                        ) {
                                            Row(
                                                modifier = m
                                                    .background(Color.White, shape = CircleShape),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                RadioButton(
                                                    selected = false,
                                                    onClick = { /*TODO*/ },
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = Color.Black,
                                                        unselectedColor = Color.Black,
                                                        disabledColor = Color.Black
                                                    ),
                                                )
                                            }
                                        }

                                    }
                                    Text(text = "Theme 5")
                                }
                            }
                        }
                    }


                }
            }

            item {
                //Wearing Position
                Column(
                    modifier = m
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .coloredShadow(color = Color.DarkGray)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp))
                        .clickable { },
                    verticalArrangement = Arrangement.Center

                ){
                    Column(
                        modifier = m
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                    ) {

                        Row(
                            modifier = m
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Wearing position",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )
                        }
                        Spacer(modifier = m.height(10.dp))
                        Text(
                            text = "Where is wearing position your smartwatch ",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = m.height(10.dp))
                        Row(
                            modifier = m
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = false,
                                onClick = { /*TODO*/ },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Black,
                                    disabledColor = Color.Black
                                ),
                            )
                            Spacer(modifier = m.width(5.dp))
                            Text(
                                text = "Left hand",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                            )
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(
                            modifier = m
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = false,
                                onClick = { /*TODO*/ },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Black,
                                    disabledColor = Color.Black
                                )
                            )
                            Spacer(modifier = m.width(5.dp))
                            Text(
                                text = "Right hand",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                            )
                        }

                    }

                }
            }


            item {
                //Health Monitoring
                Column(
                    modifier = m
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .coloredShadow(color = Color.DarkGray)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp))
                        .clickable { },
                    verticalArrangement = Arrangement.Center

                ){
                    Column(
                        modifier = m.padding(vertical = 10.dp,horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = m
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Health Monitoring",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )

                        }
                        Spacer(modifier = m.height(10.dp))
                        Text(
                            text = "Select time duration to monitoring your health, current time your select is 30 minutes",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = m.height(10.dp))
                        Button(onClick = { /*TODO*/ }, modifier = m
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)) {
                            Text(text = "Select time")
                        }
                    }

                }
            }

            item{
                //Unit Settings
                Column(
                    modifier = m
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .coloredShadow(color = Color.DarkGray)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp))
                        .clickable { },
                    verticalArrangement = Arrangement.Center

                ){
                    Column(
                        modifier = m.padding(vertical = 10.dp,horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = m
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Health Monitoring",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )

                        }
                        Spacer(modifier = m.height(10.dp))
                        Text(
                            text = "Select time duration to monitoring your health, current time your select is 30 minutes",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = m.height(10.dp))
                        Button(onClick = { /*TODO*/ }, modifier = m
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)) {
                            Text(text = "Select time")
                        }
                    }

                }
            }





        }
    }
}

@Preview
@Composable
fun PreviewPageSetting(){
    TesMultiModuleTheme {
        PageSettingSw()
    }
}


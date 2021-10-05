package com.trian.smartwatch.settings


import android.provider.CalendarContract
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
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
import com.trian.component.ui.theme.ColorFontFeatures
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
        var outline1:Color by remember {
            mutableStateOf(ColorFontFeatures)
        }
        var outline2:Color by remember {
            mutableStateOf(Color.Black)
        }
        var outline3:Color by remember {
            mutableStateOf(Color.Black)
        }
        var outline4:Color by remember {
            mutableStateOf(Color.Black)
        }
        var outline5:Color by remember {
            mutableStateOf(Color.Black)
        }
        var outline6:Color by remember {
            mutableStateOf(Color.Black)
        }
        var theme1 : Boolean by remember {
            mutableStateOf(true)
        }
        var theme2 : Boolean by remember {
            mutableStateOf(false)
        }
        var theme3 : Boolean by remember {
            mutableStateOf(false)
        }
        var theme4 : Boolean by remember {
            mutableStateOf(false)
        }
        var theme5 : Boolean by remember {
            mutableStateOf(false)
        }
        var theme6 : Boolean by remember {
            mutableStateOf(false)
        }
        var leftHand : Boolean by remember {
            mutableStateOf(false)
        }
        var rightHand : Boolean by remember {
            mutableStateOf(true)
        }
        LazyColumn(
            modifier = m
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

            item {
                //theme
                Column(
                    modifier = m
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
                                                .border(
                                                    2.dp,
                                                    outline1,
                                                    RoundedCornerShape(15.dp)
                                                )
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
                                                    selected = theme1,
                                                    onClick = {
                                                        theme1 = true
                                                        theme2 = false
                                                        theme3 = false
                                                        theme4 = false
                                                        theme5 = false
                                                        theme6 = false

                                                        if(theme1){
                                                            outline1 = ColorFontFeatures
                                                            outline2 = Color.Black
                                                            outline3 = Color.Black
                                                            outline4 = Color.Black
                                                            outline5 = Color.Black
                                                            outline6 = Color.Black
                                                        }
                                                              },

                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = ColorFontFeatures,
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
                                                .border(
                                                    2.dp,
                                                    outline2,
                                                    RoundedCornerShape(15.dp)
                                                )
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
                                                    selected = theme2,
                                                    onClick = {
                                                        theme1 = false
                                                        theme2 = true
                                                        theme3 = false
                                                        theme4 = false
                                                        theme5 = false
                                                        theme6 = false
                                                        if(theme2){
                                                            outline1 = Color.Black
                                                            outline2 = ColorFontFeatures
                                                            outline3 = Color.Black
                                                            outline4 = Color.Black
                                                            outline5 = Color.Black
                                                            outline6 = Color.Black
                                                        }
                                                              },
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = ColorFontFeatures,
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
                                                .border(
                                                    2.dp,
                                                    outline3,
                                                    RoundedCornerShape(15.dp)
                                                )
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
                                                    selected = theme3,
                                                    onClick = {
                                                        theme1 = false
                                                        theme2 = false
                                                        theme3 = true
                                                        theme4 = false
                                                        theme5 = false
                                                        theme6 = false

                                                        if(theme3){
                                                            outline1 = Color.Black
                                                            outline2 = Color.Black
                                                            outline3 = ColorFontFeatures
                                                            outline4 = Color.Black
                                                            outline5 = Color.Black
                                                            outline6 = Color.Black
                                                        }},
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = ColorFontFeatures,
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
                                                .border(
                                                    2.dp,
                                                    outline4,
                                                    RoundedCornerShape(15.dp)
                                                )
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
                                                    selected = theme4,
                                                    onClick = {
                                                        theme1 = false
                                                        theme2 = false
                                                        theme3 = false
                                                        theme4 = true
                                                        theme5 = false
                                                        theme6 = false

                                                        if(theme4){
                                                            outline1 = Color.Black
                                                            outline2 = Color.Black
                                                            outline3 = Color.Black
                                                            outline4 = ColorFontFeatures
                                                            outline5 = Color.Black
                                                            outline6 = Color.Black
                                                        }},
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = ColorFontFeatures,
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
                                                .border(
                                                    2.dp,
                                                    outline5,
                                                    RoundedCornerShape(15.dp)
                                                )
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
                                                    selected = theme5,
                                                    onClick = {
                                                        theme1 = false
                                                        theme2 = false
                                                        theme3 = false
                                                        theme4 = false
                                                        theme5 = true
                                                        theme6 = false

                                                        if(theme5){
                                                            outline1 = Color.Black
                                                            outline2 = Color.Black
                                                            outline3 = Color.Black
                                                            outline4 = Color.Black
                                                            outline5 = ColorFontFeatures
                                                            outline6 = Color.Black
                                                        }},
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = ColorFontFeatures,
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
                                                .border(
                                                    2.dp,
                                                    outline6,
                                                    RoundedCornerShape(15.dp)
                                                )
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
                                                    selected = theme6,
                                                    onClick = {
                                                        theme1 = false
                                                        theme2 = false
                                                        theme3 = false
                                                        theme4 = false
                                                        theme5 = false
                                                        theme6 = true
                                                        if(theme6){
                                                            outline1 = Color.Black
                                                            outline2 = Color.Black
                                                            outline3 = Color.Black
                                                            outline4 = Color.Black
                                                            outline5 = Color.Black
                                                            outline6 = ColorFontFeatures
                                                        }},
                                                    enabled = true,
                                                    colors = RadioButtonDefaults.colors(
                                                        selectedColor = ColorFontFeatures,
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
                        .padding(horizontal = 16.dp)
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
                                selected = leftHand,
                                onClick = {
                                    leftHand = true
                                    rightHand = false
                                          },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = ColorFontFeatures,
                                    unselectedColor = Color.Black,
                                    disabledColor = Color.Black
                                ),
                            )
                            Spacer(modifier = m.width(5.dp))
                            Text(
                                text = "Left hand",
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
                                selected = rightHand,
                                onClick = {
                                    leftHand = false
                                    rightHand = true },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = ColorFontFeatures,
                                    unselectedColor = Color.Black,
                                    disabledColor = Color.Black
                                )
                            )
                            Spacer(modifier = m.width(5.dp))
                            Text(
                                text = "Right hand",
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
                            color = Color.Black.copy(0.5f),
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
                                text = "Unit settings",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )

                        }
                        Spacer(modifier = m.height(10.dp))
                        Text(
                            text = "Set unit settings for your smartwatch",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = m.height(10.dp))

                        Row(
                            modifier = m
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = { /*TODO*/ }, modifier = m.fillMaxWidth(),) {
                                Text(
                                    text = "Distance",
                                    fontSize = 14.sp,
                                )
                            }
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(
                            modifier = m
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = { /*TODO*/ },modifier = m.fillMaxWidth()) {
                                Text(
                                    text = "Temperature",
                                    fontSize = 14.sp,
                                )
                            }

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


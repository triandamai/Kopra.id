package com.trian.component.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.*
import com.trian.domain.models.Hospital
import compose.icons.Octicons
import compose.icons.octicons.*


@Composable
fun AppBarHospital(
    modifier:Modifier = Modifier,
    color: Color=Color.White,
    elevation: Dp =0.dp,
    onSearch:()->Unit,
    onHistoryClick:()->Unit,
    onNotification:()->Unit
){
    var query by remember {
        mutableStateOf("")
    }
    TopAppBar(
        title = {
                TextField(
                    value = query,
                    leadingIcon = { Icon(Octicons.Search16, contentDescription ="" ) },
                    onValueChange = {
                        query=it
                    },
                    placeholder = {
                        Text(
                        text = "Search Hospital",
                            fontSize = 14.sp
                    )
                                  },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start=3.dp,end=3.dp,bottom = 6.dp,top = 6.dp)
                    ,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp),
            ) {
                IconButton(onClick = { 
                    onHistoryClick()
                }) {
                    Icon(
                        Octicons.ListUnordered16,
                        contentDescription = "History",
                        modifier = modifier
                            .size(16.dp),

                    )
                }
                IconButton(onClick = { 
                    onNotification()
                }) {
                    Icon(
                        Octicons.Bell16,
                        contentDescription = "Notification",
                        modifier = modifier
                            .size(16.dp),

                    )
                }
            }
        },
        backgroundColor = color,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = elevation
    )

}

@Composable
fun AppBarDetailHospital(
    hospital: Hospital,
    onBackPressed : () -> Unit,
    onNameClick: () -> Unit,
    hospitalPict : Painter,
    m:Modifier =Modifier
){
    Box(modifier = m
        .fillMaxWidth()
        .fillMaxHeight(0.26f)) {

        Image(
            painter = hospitalPict,
            contentDescription = "contentDescription",
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
                .padding(start = 12.dp, end = 12.dp, bottom = 8.dp, top = 12.dp),
        ) {
            Column() {
                Row(
                    modifier = m.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { onBackPressed()}) {
                            Icon(
                                imageVector = Octicons.ArrowLeft24,
                                contentDescription = "Arrow",
                                tint = ColorFontFeatures
                            )
                        }
                    }
                    Spacer(modifier = m.width(8.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .clickable { onNameClick() }
                    ) {

                        Text(
                            text = hospital.name,
                            color = ColorFontFeatures,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = hospital.address,
                            color = ColorFontFeatures,
                            fontSize = 11.sp,
                            maxLines = 1
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_cexup),
                        contentDescription = "",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp),
                    )
                }

            }

        }

    }

}


@Preview
@Composable
fun AppBarHospitalPriview(){
    val isDialogName= remember { mutableStateOf("false") }

    TesMultiModuleTheme(content = {
        AppBarDetailHospital(
            hospital = Hospital(
                id = 1,
                slug = "rs-tele-cexup",
                description = "",
                thumb = "",
                thumb_original = "",
                name = "RS Tele Cexup",
                address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
                others = "",
            ),
            onBackPressed = {},
            onNameClick = {},
            hospitalPict = painterResource(id = R.drawable.hospital),

            )
    })
}
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
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
fun CardOrder(modifier:Modifier=Modifier,
              order:Order,
              index:Int,
              textStyle: TextStyle = TextStyle(),
              onClick: (index: Int) -> Unit
){

    var scaledTextStyle by remember { mutableStateOf(textStyle) }
    var readyToDraw by remember { mutableStateOf(false) }

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
                .clickable { onClick(index) }
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
                            fontWeight = FontWeight.Bold
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
                    Text(
                        text = "13/09/2021",
                        modifier = modifier
                            .drawWithContent {
                                if(readyToDraw){ drawContent()
                                }
                        },
                        style = scaledTextStyle,
                        softWrap = true,
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

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Octicons.Clock16, contentDescription = "Time")
                    Spacer(modifier = modifier.width(6.dp))
                    Text(text = "10.30 AM",
                        modifier = modifier
                            .drawWithContent {
                                if(readyToDraw){ drawContent()
                                }
                            },
                        style = scaledTextStyle,
                        softWrap = true,
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Octicons.Dot24, contentDescription = "Time")
                    Spacer(modifier = modifier.width(6.dp))
                    Text(
                        text = "Confirmed",
                        modifier = modifier
                            .drawWithContent {
                                if(readyToDraw){ drawContent()
                                }
                            },
                        style = scaledTextStyle,
                        softWrap = true,
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
            Row(
                modifier= modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp,
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                TextButton(
                    modifier=modifier
                        .width(currentWidth/2 - 40.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Cancel",
                        color=Color.Gray,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                TextButton(
                    modifier=modifier
                        .width(currentWidth/2 - 40.dp),
                    onClick = { /*TODO*/ }) {
                    Text(
                        text = "Start Meeting",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }

    }
}

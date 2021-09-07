package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.ColorFontSw

/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */

@Composable
fun CardSmarthWatch(
    param: String,
    imageParam: String,
    vlastest : String,
    vmax: String,
    vmin:String,
    satuan: String,
    onClickCard: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = param, color = ColorFontSw)
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.sw_dummy),
                    contentDescription = "Oximeter",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .border(1.dp, ColorFontSw, CircleShape)
                        .padding(10.dp),
                    alignment = Alignment.Center
                )
            }
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Divider(
                    color = ColorFontSw,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    thickness = 2.dp
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 23.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = vlastest,
                        fontSize = 32.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        color = ColorFontSw
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = satuan,
                        fontSize = 24.sp,
                        fontStyle = FontStyle.Normal,
                        color = ColorFontSw
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.ArrowUpward,
                            contentDescription = "arrow up",
                            tint = Color.Green,
                        )
                        Text(
                            text = vmax,
                            fontSize = 28.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            color = ColorFontSw
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = satuan,
                            fontSize = 20.sp,
                            fontStyle = FontStyle.Normal,
                            color = ColorFontSw
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.ArrowDownward,
                            contentDescription = "arrow up",
                            tint = Color.Red,
                        )
                        Text(
                            text = vmin,
                            fontSize = 28.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            color = ColorFontSw
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = satuan,
                            fontSize = 20.sp,
                            fontStyle = FontStyle.Normal,
                            color = ColorFontSw
                        )
                    }

                }
            }

        }


    }

}
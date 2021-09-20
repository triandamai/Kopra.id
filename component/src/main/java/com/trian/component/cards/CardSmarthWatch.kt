package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.ArrowDown24
import compose.icons.octicons.ArrowUp24


/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */

@Composable
fun CardSmarthWatch(
    modifier:Modifier=Modifier,
    param: String,
    imageParam: String,
    vlastest : String,
    vmax: String,
    vmin:String,
    satuan: String,
    onClickCard: () -> Unit
) {
    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp,bottom = 8.dp,start = 16.dp,end=16.dp)
            .coloredShadow(
                color=ColorFontFeatures,
                alpha = 0.08f
            )
            .clip(RoundedCornerShape(12.dp)),

    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable { onClickCard() }
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = modifier
                        .width(currentWidth/3 - 32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = param, color = ColorFontFeatures)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.dummy_smartwatch),
                        contentDescription = param,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .padding(10.dp),
                        alignment = Alignment.Center
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Divider(
                        color = ColorFontFeatures,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp),
                        thickness = 2.dp
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start=8.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start=8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = vlastest,
                            style= TextStyle(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.SemiBold,
                            ),
                            color = ColorFontFeatures
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = satuan,
                            fontSize = 20.sp,
                            fontStyle = FontStyle.Normal,
                            color = ColorFontFeatures
                        )
                    }
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start=8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier =modifier
                                .width(currentWidth/3 - 30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Octicons.ArrowUp24,
                                contentDescription = "Max",
                                tint = Color.Green,
                            )
                            Text(
                                text = vmax,
                                style= TextStyle(
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal,
                                ),
                                color = ColorFontFeatures
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = satuan,
                                fontSize = 11.sp,
                                fontStyle = FontStyle.Normal,
                                color = ColorFontFeatures
                            )
                        }
                        Row( modifier =modifier
                            .width(currentWidth/3 - 30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Octicons.ArrowDown24,
                                contentDescription = "Min",
                                tint = Color.Red,
                            )
                            Text(
                                text = vmin,
                                style= TextStyle(
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Normal,
                                ),
                                color = ColorFontFeatures
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = satuan,
                                fontSize = 11.sp,
                                fontStyle = FontStyle.Normal,
                                color = ColorFontFeatures
                            )
                        }

                    }
                }

            }


        }
    }

}

@Preview
@Composable
fun PreviewCardSmartWatch(){
    CardSmarthWatch(
        param = "Sleep",
        imageParam = "",
        vlastest = "5.9",
        vmax = "6.7",
        vmin = "4.2",
        satuan = "hours"
    ) {

    }
}
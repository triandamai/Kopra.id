package com.trian.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.FontDeviceConnected
import com.trian.component.ui.theme.FontDeviceName
import com.trian.component.ui.theme.SelectDevicelogo
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.Devices

@ExperimentalMaterialApi
@Composable
fun CardListDevice(
    status: String,
    dateStatus: String,
    modifier: Modifier=Modifier
){

    Column( modifier=modifier.coloredShadow(color = ColorFontFeatures,alpha = 0.1f)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentWidth()
                .height(90.dp)
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.White),

            ) {
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 10.dp,
                        horizontal = 10.dp,
                    )
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = status,
                        fontSize = 12.sp,
                        color = FontDeviceConnected
                    )
                    Text(
                        text = dateStatus,
                        fontSize = 12.sp,
                        color = FontDeviceConnected
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "NB A78 - F8:DC:45:F8",
                    fontSize = 18.sp,
                    color = FontDeviceName
                )
            }
        }
    }



}
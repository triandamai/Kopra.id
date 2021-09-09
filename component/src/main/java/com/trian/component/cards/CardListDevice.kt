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
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
            .background(color = Color.White)
            .coloredShadow(color = ColorFontFeatures),
        shape = RoundedCornerShape(12.dp),
        onClick = {/*todo*/},
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight()
                    .background(SelectDevicelogo)
            ) {

            }
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
                    text = "Microlife A78",
                    fontSize = 20.sp,
                    color = FontDeviceName
                )
            }
        }

    }
}
package com.trian.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.trian.component.picker.MyNumberPicker
import com.trian.component.picker.NumberPickerBp
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme

@Composable
fun BottomSheetBpCalibration(
    min:Int,
    max:Int,
    onValueChange :(old: Int, new:Int)->Unit,
    modifier:Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Color.Transparent,
                shape = RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                ),
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(Color(0xFFF0F0F0))
                    .height(10.dp)
                    .width(90.dp),
            )

        }
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cancel",
                color = ColorFontFeatures,
                modifier = modifier.clickable {

                },
            )
            Text(text = "Blood presure calibration")
            Text(
                text = "Done",
                color = ColorFontFeatures,
                modifier = modifier.clickable {
                },
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "SBP")
                Spacer(modifier = modifier.width(10.dp))
                NumberPickerBp(
                    min = 45,
                    max = 150,
                    onValueChange = {old, new ->  })
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                NumberPickerBp(
                    min = min,
                    max = max,
                    onValueChange = {old, new ->  })
                Spacer(modifier = modifier.width(10.dp))
                Text(text = "DBP")
            }

        }
    }
}

@Preview
@Composable
fun PreviewBt(){
    TesMultiModuleTheme {
        BottomSheetBpCalibration(min = 45, max = 150, onValueChange = {old, new ->  })
    }
}
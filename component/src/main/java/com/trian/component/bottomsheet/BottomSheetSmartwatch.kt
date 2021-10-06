package com.trian.component.bottomsheet

import android.widget.NumberPicker
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.component.R
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme


@ExperimentalAnimationApi
@Composable
fun BottomSheetHealthMonitoring(
    namePicker: String,
    modifier: Modifier = Modifier,
){
    val timeIntervalMonitoring: Array<String> by remember {
        mutableStateOf(arrayOf("10","20","30","40","50","60"))
    }
    val distance: Array<String> by remember {
        mutableStateOf(arrayOf("Mile","Km","M"))
    }
    val temperature: Array<String> by remember {
        mutableStateOf(arrayOf("°C","°F"))
    }
    val name by remember {
        mutableStateOf(namePicker)
    }
    when(name){
        "Health Monitoring" ->{
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Cancel",color = Color.Transparent)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = modifier
                                .clip(shape = RoundedCornerShape(5.dp))
                                .background(Color(0xFFF0F0F0))
                                .height(10.dp)
                                .width(90.dp),
                        )
                    }
                    Text(text = "Done",color = Color.Transparent)
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Cancel",
                        color = ColorFontFeatures,
                        modifier = modifier.clickable { },
                    )
                    Text(text = namePicker)
                    Text(
                        text = "Done",
                        color = ColorFontFeatures,
                        modifier = modifier.clickable { },
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Cancel",color = Color.Transparent)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        MyNumberPicker(min = 0, max = timeIntervalMonitoring.lastIndex,{ old, new -> }, array = timeIntervalMonitoring)
                        Spacer(modifier = modifier.width(5.dp))
                        Text(text = "mins", fontSize = 14.sp)
                    }
                    Text(text = "Done",color = Color.Transparent)
                }


            }
        }
        "Distance" ->{
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Cancel",color = Color.Transparent)
                    Row(
                        modifier = modifier
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(Color(0xFFF0F0F0))
                            .height(10.dp)
                            .width(80.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                    }
                    Text(text = "Done", color = Color.Transparent)
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cancel",
                        color = ColorFontFeatures,
                        modifier = modifier.clickable { },
                    )
                    Text(text = namePicker)
                    Text(
                        text = "Done",
                        color = ColorFontFeatures,
                        modifier = modifier.clickable { },
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Cancel",color = Color.Transparent)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        MyNumberPicker(min = 0, max = distance.lastIndex,{ old, new -> }, array = distance)
                    }
                    Text(text = "Done",color = Color.Transparent)
                }


            }
        }
        "Temperature" ->{
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Cancel",color = Color.Transparent)
                    Row(
                        modifier = modifier
                            .clip(shape = RoundedCornerShape(5.dp))
                            .background(Color(0xFFF0F0F0))
                            .height(10.dp)
                            .width(80.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                    }
                    Text(text = "Done", color = Color.Transparent)
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Cancel",
                        color = ColorFontFeatures,
                        modifier = modifier.clickable { },
                    )
                    Text(text = namePicker)
                    Text(
                        text = "Done",
                        color = ColorFontFeatures,
                        modifier = modifier.clickable { },
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Cancel",color = Color.Transparent)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        MyNumberPicker(min = 0, max = temperature.lastIndex,{ old, new -> }, array = temperature)
                    }
                    Text(text = "Done",color = Color.Transparent)
                }


            }
        }
    }



}



@Composable
fun MyNumberPicker(
    min: Int,
    max: Int,
    onValueChange: (old: Int, new: Int) -> Unit,
    array: Array<String>
) {
    var arr by remember {
        mutableStateOf(array)
    }
    AndroidView({
        NumberPicker(
            ContextThemeWrapper(it, R.style.Chart)

        )
    }, update = {

            view ->
        view.maxValue = max
        view.minValue = min
        view.displayedValues = arr
        view.setOnValueChangedListener { _, oldval, newval ->
            onValueChange(oldval, newval)
        }
    })
}
@Preview
@Composable
fun PreviewBottom(){
    TesMultiModuleTheme {
//        BottomSheetHealthMonitoring(namePicker = "Temperature")

    }
}

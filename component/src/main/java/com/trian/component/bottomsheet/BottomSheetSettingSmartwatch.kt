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
import androidx.navigation.compose.navArgument
import com.trian.common.utils.sdk.SDKConstant
import com.trian.component.R
import com.trian.component.picker.MyNumberPicker
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme


@ExperimentalAnimationApi
@Composable
fun BottomSheetSettingSmartwatch(
    namePicker: String,
    modifier: Modifier = Modifier,
    default:Int,
    onCancel:()->Unit,
    onSelected:(old:Int,selected:Int)->Unit
){
        val timeIntervalMonitoring: Array<String> =arrayOf("10","20","30","40","50","60")
        val distance: Array<String> = arrayOf("Mile","Km")
        val temperature: Array<String> =arrayOf("°C","°F")
        val value = when(namePicker){
            "Health Monitoring" ->0
            "Distance" -> when(default){
                SDKConstant.UNIT.KM -> 1
                else -> SDKConstant.UNIT.MILE
            }
            "Temperature" -> when(default){
                SDKConstant.UNIT.CELCIUS->0
                else->1
            }
            else -> 0
        }
        var selected by remember {
            mutableStateOf(value)
        }
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
                                .background(Color.LightGray.copy(alpha = 0.6f))
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
                            onCancel()
                        },
                    )
                    Text(text = namePicker)
                    Text(
                        text = "Done",
                        color = ColorFontFeatures,
                        modifier = modifier.clickable {
                            onSelected(default,selected)
                        },
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        MyNumberPicker(
                            value=value,
                            min = 0,
                            max = when(namePicker){
                                "Health Monitoring" ->timeIntervalMonitoring.lastIndex
                                "Distance" -> distance.lastIndex
                                "Temperature" -> temperature.lastIndex
                                else -> 0
                            },
                            items =  when(namePicker){
                                "Health Monitoring" ->timeIntervalMonitoring
                                "Distance" -> distance
                                "Temperature" -> temperature
                                else -> arrayOf()
                            }){
                                old, new ->
                            selected = new

                        }
                        if(namePicker == "Health Monitoring") {
                            Spacer(modifier = modifier.width(5.dp))
                            Text(text = "mins", fontSize = 14.sp)
                        }
                    }

        }
    }
}

@Preview
@Composable
fun PreviewBottom(){
    TesMultiModuleTheme {
//        BottomSheetHealthMonitoring(namePicker = "Temperature")

    }
}

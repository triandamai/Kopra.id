package com.trian.smartwatch.ui.settings


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.route.Routes
import com.trian.common.utils.sdk.SDKConstant
import com.trian.component.appbar.AppBarDetail
import com.trian.component.cards.CardItemSmartwatchTheme
import com.trian.component.datum.listThemeSmartwatch
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.component.utils.coloredShadow
import com.trian.data.viewmodel.SmartWatchViewModel
import kotlinx.coroutines.CoroutineScope


@ExperimentalAnimationApi
@Composable
fun PageSettingSmartwatch(
    modifier:Modifier = Modifier,
    nav: NavController,
    viewModel: SmartWatchViewModel,
    scope:CoroutineScope
){
    val selectedIndexTheme by viewModel.themeWatch
    var selectedTheme by remember {
        mutableStateOf(listThemeSmartwatch[selectedIndexTheme])
    }


    var wearingPosition by viewModel.wearingPosition

    fun setWearingPosition(position: Int){
        viewModel.settingWearingPosition(position)
    }
    //Style(0-(N-1)), N represents the number of main interfaces supported by the device，and is queried by the get command
    fun setDefaultTheme(style:Int){
        viewModel.settingTheme(style)
    }


    Scaffold(
        topBar = {AppBarDetail(page = "Smartwatch Settings", onBackPressed = {
            nav.popBackStack()
        })},
        backgroundColor = LightBackground
    ) {

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

            item {
                //theme
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .coloredShadow(color = ColorFontFeatures)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp))

                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Choose your theme",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )
                        }
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Select theme to make your smartwatch awesome ",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        LazyRow(
                            modifier = modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
                        ){
                            items(listThemeSmartwatch.size){
                                index:Int->
                                CardItemSmartwatchTheme(
                                    selected=selectedTheme.index == listThemeSmartwatch[index].index,
                                    smartwatchThemeModel = listThemeSmartwatch[index],
                                    onSelect = {
                                        selectedTheme = it
                                        setDefaultTheme(it.index)
                                    }
                                )
                            }
                        }
                    }


                }
            }


            item {
                //Wearing Position
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .coloredShadow(color = Color.DarkGray)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp)),
                    verticalArrangement = Arrangement.Center

                ){
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                    ) {

                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Wearing position",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )
                        }
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Where is wearing position your smartwatch ",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = modifier.height(14.dp))
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = wearingPosition == SDKConstant.WEARING_POSITION.LEFT,
                                onClick = {
                                   wearingPosition = SDKConstant.WEARING_POSITION.LEFT
                                    setWearingPosition(SDKConstant.WEARING_POSITION.LEFT)
                                },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = ColorFontFeatures,
                                    unselectedColor = Color.Black,
                                    disabledColor = Color.Black
                                ),
                            )
                            Spacer(modifier = modifier.width(5.dp))
                            Text(
                                text = "Left hand",
                                fontSize = 14.sp,
                            )
                        }
                        Spacer(modifier = modifier.height(10.dp))
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = wearingPosition ==  SDKConstant.WEARING_POSITION.RIGHT,
                                onClick = {
                                    wearingPosition = SDKConstant.WEARING_POSITION.RIGHT
                                    setWearingPosition(SDKConstant.WEARING_POSITION.RIGHT)

                                },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = ColorFontFeatures,
                                    unselectedColor = Color.Black,
                                    disabledColor = Color.Black
                                )
                            )
                            Spacer(modifier = modifier.width(5.dp))
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
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .coloredShadow(color = ColorFontFeatures)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp)),
                    verticalArrangement = Arrangement.Center

                ){
                    Column(
                        modifier = modifier.padding(vertical = 10.dp,horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Health Monitoring",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )

                        }
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Select time duration to monitoring your health, current time your select is 30 minutes",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f),
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Button(onClick = {
                            nav.navigate(Routes.SmartwatchRoute.BOTTOMSHEET_HEALTH_MONITORING){
                                launchSingleTop = true
                                restoreState = false
                                popUpTo(Routes.SmartwatchRoute.BOTTOMSHEET_DISTANCE){inclusive=true}
                                popUpTo(Routes.SmartwatchRoute.BOTTOMSHEET_TEMPERATURE){inclusive=true}
                            }
                        }, modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)) {
                            Text(text = "Select time")
                        }
                        Spacer(modifier = modifier.height(14.dp))
                    }

                }
            }

            item{
                //Unit Settings
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .coloredShadow(color = ColorFontFeatures)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clip(shape = RoundedCornerShape(12.dp)),
                    verticalArrangement = Arrangement.Center

                ){
                    Column(
                        modifier = modifier.padding(vertical = 10.dp,horizontal = 16.dp)
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Unit settings",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                            )

                        }
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Set unit settings for your smartwatch",
                            fontSize = 14.sp,
                            color = Color.Black.copy(0.5f)
                        )
                        Spacer(modifier = modifier.height(14.dp))

                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = {
                                nav.navigate(Routes.SmartwatchRoute.BOTTOMSHEET_DISTANCE){
                                    launchSingleTop = true
                                    restoreState = false
                                    popUpTo(Routes.SmartwatchRoute.BOTTOMSHEET_TEMPERATURE){inclusive=true}
                                    popUpTo(Routes.SmartwatchRoute.BOTTOMSHEET_HEALTH_MONITORING){inclusive=true}
                                }

                            }, modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp),) {
                                Text(
                                    text = "Distance",
                                    fontSize = 14.sp,
                                )
                            }
                        }
                        Spacer(modifier = modifier.height(10.dp))
                        Row(
                            modifier = modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = {
                                nav.navigate(Routes.SmartwatchRoute.BOTTOMSHEET_TEMPERATURE){
                                    launchSingleTop = true
                                    restoreState = false
                                    popUpTo(Routes.SmartwatchRoute.BOTTOMSHEET_DISTANCE){inclusive=true}
                                    popUpTo(Routes.SmartwatchRoute.BOTTOMSHEET_HEALTH_MONITORING){inclusive=true}
                                }

                            },modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp)) {
                                Text(
                                    text = "Temperature",
                                    fontSize = 14.sp,
                                )
                            }

                        }
                        Spacer(modifier = modifier.height(14.dp))
                    }

                }
            }





        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewPageSetting(){
    val nav : NavController = rememberAnimatedNavController()
    TesMultiModuleTheme {
        PageSettingSmartwatch(nav = nav,viewModel = viewModel(),scope = rememberCoroutineScope())
    }
}


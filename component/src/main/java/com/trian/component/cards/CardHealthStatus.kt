package com.trian.component.cards

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.trian.component.R
import com.trian.component.chart.CircularChartHealthStatus
import com.trian.component.ui.theme.*
import com.trian.component.utils.coloredShadow
import com.trian.data.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 03/09/2021
 */

@ExperimentalAnimationApi
@Composable
fun CardHealthStatus(
    modifier: Modifier = Modifier,
    state: MutableTransitionState<Boolean>,
    viewModel:MainViewModel,
    scope:CoroutineScope
){
    val bloodPressure by viewModel.latestBloodPressure
    val bloodOxygen by viewModel.latestBloodOxygen
    val respiration by viewModel.latestRespiration
    val temperature by viewModel.latestTemperature
    val heartRate by viewModel.latestHeartRate

    SideEffect {
        scope.launch(context = Dispatchers.IO){
            viewModel.getHealthStatus()
        }
    }

    val density = LocalDensity.current
    AnimatedVisibility(
        visibleState = state,
        enter = slideInVertically(
            // Slide in from 40 dp from the top.
            initialOffsetY = { with(density) { -40.dp.roundToPx() } }
        ) + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()) {


           Column(
               modifier = modifier
                   .background(Color.Transparent)
                   .fillMaxWidth()
                   .padding(horizontal = 16.dp, vertical = 16.dp)
                   .height(230.dp)
                   .coloredShadow(
                       color = ColorFontFeatures,
                       alpha = 0.1f
                   )
                   .clipToBounds()
                   .clip(
                       RoundedCornerShape(
                           topStart = 8.dp,
                           topEnd = 50.dp,
                           bottomStart = 8.dp,
                           bottomEnd = 8.dp
                       )
                   ),
           ) {
               Column(
                   modifier = modifier
                       .fillMaxWidth()
                       .background(Color.White)
                       .fillMaxHeight()
                       .padding(horizontal = 24.dp, vertical = 24.dp)
               ) {

                   //upper
                   Row(
                       modifier = modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween,
                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Column(verticalArrangement = Arrangement.SpaceBetween) {
                           ItemBottomHealthStatusCard(type = TypeItemHealthStatus.ROW,name = "BPM",value = "${bloodPressure.value_systole}/${bloodPressure.value_diastole}")
                           Spacer(modifier = modifier.height(10.dp))
                           ItemBottomHealthStatusCard(type = TypeItemHealthStatus.ROW,name = "Spo2",value = "${bloodOxygen.value_blood_oxygen}")

                       }
                       //chart rounded
                       CircularChartHealthStatus(percent = 0.8f, number = 100)
                   }
                   Spacer(modifier = modifier.height(10.dp))
                   //divider
                   Divider(
                       modifier
                           .fillMaxWidth()
                           .padding(vertical = 6.dp)
                   )
                   Spacer(
                       modifier = modifier.height(10.dp)
                   )
                   //bottom
                   Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                       ItemBottomHealthStatusCard(type = TypeItemHealthStatus.COLUMN,name="Temp",value="${temperature.value_temperature}")
                       ItemBottomHealthStatusCard(type = TypeItemHealthStatus.COLUMN,name="Resp",value="${respiration.value_respiration}")
                       ItemBottomHealthStatusCard(type = TypeItemHealthStatus.COLUMN,name="HR",value="${respiration.value_heart_rate}")
                   }
               }

           }

    }
}

@Composable
fun ItemBottomHealthStatusCard(modifier: Modifier = Modifier,name:String,value:String,type:TypeItemHealthStatus){
    when(type){
        TypeItemHealthStatus.COLUMN->{
            Column {
                Text(text = name,color = ColorGray)
                Box() {
                    Box(modifier = modifier
                        .height(2.dp)
                        .width(20.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    MaterialTheme.colors.primary.copy(alpha = .3f),
                                    MaterialTheme.colors.primaryVariant
                                )
                            )
                        )
                        .clip(shape = RoundedCornerShape(10.dp)),
                    ){}
                    Box(modifier = modifier
                        .height(2.dp)
                        .width(40.dp)
                        .background(color = MaterialTheme.colors.primary.copy(alpha = .2f))
                        .clip(shape = RoundedCornerShape(10.dp)),
                    ){}
                }
                Text(text = value,style = TextStyle(fontSize = 24.sp))
            }
        }
        TypeItemHealthStatus.ROW->{
            Row {
                Card(
                    backgroundColor = Color.Blue.copy(alpha = 0.2f),
                    modifier = Modifier
                        .height(38.dp)
                        .width(2.dp),
                    shape = RoundedCornerShape(5.dp)
                ){}
                Spacer(modifier = modifier.width(5.dp))
                Column {
                    Text(text = name,color = ColorGray)
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Image(
                            painter = painterResource(id = R.drawable.dummy_smartwatch) ,
                            contentDescription = "",
                            modifier= modifier
                                .width(10.dp)
                                .height(10.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = value,style = TextStyle(fontSize = 24.sp))
                    }
                }
            }
        }
    }
}

enum class TypeItemHealthStatus{
    COLUMN,
    ROW
}


@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewHealthStatus(){
    CardHealthStatus(
        state = MutableTransitionState(false),
        viewModel = viewModel(),
        scope = rememberCoroutineScope()
    )
}
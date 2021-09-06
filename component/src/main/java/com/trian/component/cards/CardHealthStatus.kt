package com.trian.component.cards

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.chart.CircularChartHealthStatus
import com.trian.component.ui.theme.*

/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 03/09/2021
 */

@Composable
fun CardHealthStatus(modifier: Modifier = Modifier){
    Column(modifier = modifier
        .background(Color.Transparent)
        .fillMaxWidth()
        .padding(horizontal = 50.dp)
        .height(200.dp)
        .clipToBounds()
        .clip(
            RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 50.dp,
                bottomStart = 8.dp,
                bottomEnd = 8.dp
            )
        )
    ) {
        Column(modifier= modifier
            .fillMaxWidth()
            .background(Color.White)
            .fillMaxHeight()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
            
            //upper
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                 ItemBottomHealthStatusCard(type = TypeItemHealthStatus.ROW)
                 Spacer(modifier = modifier.height(10.dp))
                 ItemBottomHealthStatusCard(type = TypeItemHealthStatus.ROW)

                }
                //chart rounded
                    CircularChartHealthStatus(percent = 0.8f, number = 80)
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
               ItemBottomHealthStatusCard(type=TypeItemHealthStatus.COLUMN)
               ItemBottomHealthStatusCard(type=TypeItemHealthStatus.COLUMN)
               ItemBottomHealthStatusCard(type=TypeItemHealthStatus.COLUMN)
            }
        }

    }
}

@Composable
fun ItemBottomHealthStatusCard(modifier: Modifier = Modifier,type:TypeItemHealthStatus){
    when(type){
        TypeItemHealthStatus.COLUMN->{
            Column {
                Text(text = "Carbs",color = ColorGray)
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
                            )).clip(shape = RoundedCornerShape(10.dp)),
                    ){}
                    Box(modifier = modifier
                        .height(2.dp)
                        .width(40.dp)
                        .background(color = MaterialTheme.colors.primary.copy(alpha = .2f)).clip(shape = RoundedCornerShape(10.dp)),
                    ){}
                }
                Text(text = "12kg")
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
                    Text(text = "Eaten",color = ColorGray)
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Image(
                            painter = painterResource(id = R.drawable.sw_dummy) ,
                            contentDescription = "",
                            modifier= modifier
                                .width(10.dp)
                                .height(10.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "113/75")
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


@Preview
@Composable
fun PreviewHealthStatus(){
    CardHealthStatus()
}
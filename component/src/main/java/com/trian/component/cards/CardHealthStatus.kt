package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.R

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
        .padding(horizontal = 16.dp)
        .height(200.dp)
        .clipToBounds()
        .clip(RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 50.dp,
            bottomStart = 8.dp,
            bottomEnd = 8.dp))
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
                 ItemBottomHealthStatusCard(type = TypeItemHealthStatus.ROW)

                }
                //chart rounded
                Column(
                    Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)) {

                }
            }
            //divider
            Divider(
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp))
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
                Text(text = "Card")
                Column(
                    modifier
                        .height(2.dp)
                        .width(10.dp)
                        .background(Color.Blue)) {

                }
                Text(text = "12kg")
            }
        }
        TypeItemHealthStatus.ROW->{
            Row {
                Column(modifier = modifier
                    .height(10.dp)
                    .width(2.dp)
                    .background(Color.Blue)
                ) {

                }
                Column {
                    Text(text = "Eaten")
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.sw_dummy) ,
                            contentDescription = "",
                            modifier= modifier
                                .width(10.dp)
                                .height(10.dp)
                        )
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
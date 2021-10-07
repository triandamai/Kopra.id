package com.trian.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontCardFeatures
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme


@Composable
fun cardSport(
    modifier: Modifier = Modifier,
    valueStep : String,
    valueKcal :String,
    valueDistance : String,
    valueDuration : String
){
    Box(
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Step target",
                color = ColorFontFeatures.copy(alpha = 0.5f),
                fontSize = 14.sp
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = valueStep,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorFontFeatures
                )
                Spacer(modifier = modifier.width(5.dp))
                Text(
                    text = "step",
                    fontSize = 22.sp,
                    color = ColorFontFeatures
                )
            }
            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.calorie),
                        contentDescription = "",
                        modifier = modifier
                            .size(28.dp)
                            .clip(shape = CircleShape)
                            .border(
                                1.dp,
                                color = ColorFontCardFeatures,
                                shape = CircleShape
                            )
                            .padding(3.dp),
                        tint = ColorFontCardFeatures
                    )
                    Spacer(modifier = modifier.width(5.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Calorie",
                            color = ColorFontFeatures,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "$valueKcal kcal",
                            color = ColorFontFeatures.copy(alpha = 0.5f),
                            fontSize = 12.sp,
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.distance),
                        contentDescription = "",
                        modifier = modifier
                            .size(28.dp)
                            .clip(shape = CircleShape)
                            .border(
                                1.dp,
                                color = ColorFontCardFeatures,
                                shape = CircleShape
                            )
                            .padding(3.dp),
                        tint = ColorFontCardFeatures
                    )
                    Spacer(modifier = modifier.width(5.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Distance",
                            color = ColorFontFeatures,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "$valueDistance km",
                            color = ColorFontFeatures.copy(alpha = 0.5f),
                            fontSize = 12.sp,
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.duration),
                        contentDescription = "",
                        modifier = modifier
                            .size(28.dp)
                            .clip(shape = CircleShape)
                            .border(
                                1.dp,
                                color = ColorFontCardFeatures,
                                shape = CircleShape
                            )
                            .padding(3.dp),
                        tint = ColorFontCardFeatures
                    )
                    Spacer(modifier = modifier.width(5.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Duration",
                            color = ColorFontFeatures,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "$valueDuration mins",
                            color = ColorFontFeatures.copy(alpha = 0.5f),
                            fontSize = 12.sp,
                        )
                    }
                }
            }
            
        }
    }


}

@Preview
@Composable
fun PreviewCardSport(){
    TesMultiModuleTheme {
        cardSport(
            valueStep = "1000",
            valueKcal = "123",
            valueDistance = "2",
            valueDuration = "20",
        )
    }
}
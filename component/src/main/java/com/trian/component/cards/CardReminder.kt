package com.trian.component.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.trian.component.ui.theme.ColorGray
import com.trian.component.R
import com.trian.component.utils.formatReadableDate
import com.trian.data.model.Reminder


@Composable
fun CardReminder(
    modifier: Modifier = Modifier,
    index:Int=0,
    reminder: Reminder,
    onClick:(index:Int,reminder:Reminder)->Unit
){
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .clickable {
                       onClick(index, reminder)
            },
        elevation=0.1.dp,
        border = BorderStroke(
            width=1.dp,
            color = ColorGray.copy(alpha = 0.5f)
        )
    ){
        Row(
            modifier= modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 0.dp,
                modifier = modifier
                        .width(80.dp)
                        .height(80.dp)
                ,
                border = BorderStroke(0.5.dp, ColorGray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bell), contentDescription = "",
                    modifier = modifier
                        .alpha(0.9f)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(10.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = modifier.width(10.dp))
            Column() {
                Text(
                    text = reminder.title,
                    style= MaterialTheme.typography.h1.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = reminder.due.formatReadableDate(),
                    style= MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        color = ColorGray,
                        letterSpacing = 0.1.sp
                    )
                )
            }
        }
    }
}
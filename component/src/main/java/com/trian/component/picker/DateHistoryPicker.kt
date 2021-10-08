package com.trian.component.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.ColorFontFeatures

@Composable
fun DateHistoryPicker(
    modifier: Modifier = Modifier,
    date:String="Mon,Sep 14",
    onClickCalender: () -> Unit,
    onNext:()->Unit,
    onPrev:()->Unit
){
    //calender
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconToggleButton(checked = false, onCheckedChange = {
            onPrev()
        }) {
            Icon(
                Icons.Filled.ArrowBackIos,
                contentDescription = "Previous Date",
                tint = ColorFontFeatures,
            )
        }
        Text(
            text = date,
            modifier = modifier
                .clickable { onClickCalender() },
            textAlign = TextAlign.Center,
            color = ColorFontFeatures,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        IconToggleButton(checked = false, onCheckedChange = {
            onNext()
        }) {
            Icon(
                Icons.Filled.ArrowForwardIos,
                contentDescription = "Next Date",
                tint = ColorFontFeatures,
            )
        }

    }
}

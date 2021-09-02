package com.trian.component.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun CardHeaderSection(modifier: Modifier=Modifier,title:String,moreText:String,onMoreClick:()->Unit){

    Row(
        modifier = modifier.padding(horizontal = 16.dp,vertical = 8.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //subtitle
        Text(text = "Mediteranian diet")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Details")
            IconToggleButton(checked = false, onCheckedChange = {}) {
                Icon(Icons.Outlined.ArrowForward,contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
fun PreviewHeaderSection(){
CardHeaderSection(title="Ini title",moreText = "ini more",onMoreClick = {

})
}
package com.trian.component.cards

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.ArrowRightAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.ArrowRight16

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */

@Composable
fun CardHeaderSection(modifier: Modifier=Modifier,title:String,moreText:String,onMoreClick:()->Unit){

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //subtitle
        Text(text = title,style = TextStyle(
            color = Color.DarkGray,
            fontSize = 18.sp
        ))
        Row(
            modifier= modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable { onMoreClick() }
                .wrapContentHeight()
                .padding(horizontal = 8.dp,vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Text(
                text = moreText,
                style = TextStyle(color = Color.DarkGray))
            Spacer(
                modifier = modifier.width(4.dp).height(4.dp))
            Icon(
                imageVector = Octicons.ArrowRight16,
                    contentDescription = "Click form more about $title",
                    modifier = modifier
            )

        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewHeaderSection(){
CardHeaderSection(title="Ini title",moreText = "ini more",onMoreClick = {

})
}
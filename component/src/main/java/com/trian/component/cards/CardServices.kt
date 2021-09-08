package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.domain.models.Service
import com.trian.component.R
import com.trian.component.ui.theme.ColorFontSw
import com.trian.component.utils.coloredShadow

/**
 * Component Services
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */

@Composable
fun CardServices(modifier:Modifier=Modifier, service: Service, index:Int, onClick:(service:Service)->Unit){

        Column(
            modifier = modifier
                .padding(
                    start = when (index) {
                        0 -> 8.dp
                        else -> 8.dp
                    }
                )
                .height(120.dp)
                .width(120.dp)
                .coloredShadow(
                    color = ColorFontSw,
                    alpha = 0.1f
                )
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
        ) {
            Column(
                modifier=modifier
                    .clickable { onClick(service) }) {
                Column(modifier=modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id=service.icon),
                        contentDescription = "Services ${service.title}",
                        modifier = modifier
                            .height(80.dp)
                            .width(80.dp))
                    Text(
                        text = service.title,
                        style = TextStyle(fontSize = 16.sp)
                    )
                }

            }
        }

}

@Preview
@Composable
fun PreviewCardServices(){
    CardServices(service = Service("Telemedicine",R.drawable.logo_cexup),onClick = {},index=1)
}
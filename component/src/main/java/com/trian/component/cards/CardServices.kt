package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.domain.models.Service
import com.trian.component.R

/**
 * Component Services
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun CardServices(modifier: Modifier = Modifier,service: Service,onClick:(service:Service)->Unit){
    Column(modifier = modifier
        .clip(RoundedCornerShape(8.dp))
        .background(color = Color.White)
        .clickable {
            onClick(service)
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Spacer(modifier = modifier.height(10.dp))
        Image(
            painter = painterResource(id = service.icon),
            modifier = modifier
                .width(50.dp)
                .height(50.dp),
            contentDescription = "Service ${service.title}"
        )
        Spacer(modifier = modifier.height(10.dp))
        Text(text = service.title,modifier=modifier.padding(horizontal = 6.dp,vertical = 6.dp))
    }
}

@Preview
@Composable
fun PreviewCardServices(){
    CardServices(service = Service("Telemedhicine",R.drawable.logo_cexup),onClick = {})
}
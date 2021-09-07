package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.domain.models.Service
import com.trian.component.R

/**
 * Component Services
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */

@Composable
fun CardServices(m:Modifier=Modifier,service: Service,onClick:(service:Service)->Unit){
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp,
        modifier = m.clickable { onClick(service) }
            .padding(horizontal = 6.dp)
    ){
        Column(
            modifier = m.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id=R.drawable.logo_cexup),
                contentDescription = "",
                modifier = m
                    .height(80.dp)
                    .width(80.dp))
            Text(text = service.title,style = MaterialTheme.typography.body1)
        }
    }
}

@Preview
@Composable
fun PreviewCardServices(){
    CardServices(service = Service("Telemedicine",R.drawable.logo_cexup),onClick = {})
}
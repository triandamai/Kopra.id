package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.R

@Composable
fun CardHospital(m:Modifier=Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = m.padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_cexup),
            contentDescription = "",
            modifier = m
                .height(50.dp)
                .width(50.dp)
        )
        Spacer(modifier = m.width(15.dp))
        Text(text = "Rs Tele Cexup",color = Color.Red)
    }
}

@Preview
@Composable
fun PreviewCardHospital(){
    CardHospital()
}
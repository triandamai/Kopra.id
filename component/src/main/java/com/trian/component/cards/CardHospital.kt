package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.*
import com.trian.domain.models.Hospital

@Composable
fun CardHospital(m:Modifier=Modifier,hospital:Hospital){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = m.padding(10.dp)
    ) {
       Box(modifier = m
           .background(color = Color.White, shape = RoundedCornerShape(8.dp))) {
           Box(modifier = m.background(color = Color.Black.copy(alpha = 0.1f),shape = RoundedCornerShape(8.dp)).padding(5.dp)) {
               Image(
                   painter = painterResource(id = R.drawable.logo_cexup),
                   contentDescription = "",
                   modifier = m
                       .height(80.dp)
                       .width(80.dp),
               )
           }
       }
        Spacer(modifier = m.width(20.dp))
        Column() {
            Text(text = hospital.name,color = Color.Black,
                style = MaterialTheme.typography.h1.copy(fontSize = 18.sp,fontWeight = FontWeight.W400))
            Text(text = hospital.address,color = ColorGray,
                style = MaterialTheme.typography.h1.copy(fontSize = 15.sp,fontWeight = FontWeight.W400),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCardHospital(){
    CardHospital(
        hospital = Hospital(
            id = 1,
            slug = "rs-tele-cexup",
            description = "",
            thumb = "",
            thumbOriginal = "",
            name = "RS Tele Cexup",
            address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
            others = "",
        )
    )
}
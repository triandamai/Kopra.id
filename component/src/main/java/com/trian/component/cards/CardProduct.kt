package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.*
import com.trian.domain.models.Product

@Composable
fun CardProduct(m: Modifier = Modifier,product:Product,index:Int,onClick:(product:Product)->Unit){
    Column(modifier= m
        .clickable{}
        .clip(RoundedCornerShape(12.dp))
        .padding(start = when(index){
            0-> 12.dp
            else -> 8.dp
        },end = 12.dp
        )
        .background(Color.White)
        .width(180.dp)
        .height(180.dp)
    ) {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.doctor_dummy),
                    contentDescription = "",
                    modifier = m
                        .fillMaxWidth()
                        .height((180 / 2).dp),
                    contentScale = ContentScale.Crop,
                )
                Icon(
                    Icons.Filled.Info,"",
                    tint = Color.White,
                    modifier= m
                        .align(Alignment.TopEnd)
                        .padding(10.dp))
            }

            Column(modifier=m.fillMaxWidth().padding(horizontal = 8.dp,vertical = 8.dp)) {
                Text(text = product.title,fontSize = 16.sp,style = MaterialTheme.typography.body1)

                Text(
                    text = product.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = ColorGray
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = m
                        .fillMaxWidth()
                ) {
                    Text(text = "IDR 24.900",fontSize = 12.sp,fontWeight = FontWeight.Bold)
                }
            }
        }
}

@Preview
@Composable
fun PreviewCardProduct(){
    CardProduct(
        product = Product(
            title = "Thermometer",
            thumb = "",
            slug = "thermometer",
            description = "Premium pepperoni anith .",
            id = 1,
            category =11,
            link = "https://google.com",
            original = "Hello World",
            price = "23.90",
            stock = 1,
            view = 11,),
        index=0,
        onClick = {}
    )
}
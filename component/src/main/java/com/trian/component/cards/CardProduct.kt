package com.trian.component.cards

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.*
import com.trian.domain.models.Product

@Composable
fun CardProduct(m: Modifier = Modifier,product:Product,onClick:(product:Product)->Unit){
    Card(shape = RoundedCornerShape(percent = 5)){
        Column() {
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.doctor_dummy),
                    contentDescription = "",
                    modifier = m.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
                Icon(
                    Icons.Filled.Info,"",
                    tint = Color.White,
                    modifier= m
                        .align(Alignment.TopEnd)
                        .padding(10.dp))
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = m
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 10.dp),verticalAlignment = Alignment.CenterVertically) {
                Text(text = product.title,fontSize = 20.sp,style = MaterialTheme.typography.body1)
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(Icons.Filled.Fireplace,"",tint = Color.Red)
                    Icon(Icons.Filled.Fireplace,"",tint = Color.Red)
                }
            }
            Text(
                text = product.description,
                modifier = m.padding(start = 24.dp,end = 24.dp,bottom = 20.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = ColorGray
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = m
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 10.dp)
            ) {
             Text(text = "IDR 24.900",fontSize = 18.sp,fontWeight = FontWeight.Bold)
             Button(onClick = { onClick(product) },colors = ButtonDefaults.buttonColors(backgroundColor = ColorGreen)) {
                 Text(text = "ORDER",color = TextWhite)
             }
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
            description = "Premium pepperoni and cheese is made with real mozzarella on original hand-tossed crust. Premium pepperoni and cheese is made with real mozzarella on original hand-tossed crust.",
            id = 1,
            category =11,
            link = "https://google.com",
            original = "Hello World",
            price = "23.90",
            stock = 1,
            view = 11,),
        onClick = {}
    )
}
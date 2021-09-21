package com.trian.module.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.coloredShadow
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24
import compose.icons.octicons.KebabHorizontal24
import compose.icons.octicons.StarFill24

@Composable
fun PageDetailOrder(m:Modifier = Modifier){
    Scaffold(
        backgroundColor = LightBackground,
        topBar = {
            Row(
                modifier = m
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Card(
                    shape = RoundedCornerShape(8.dp)
                ){
                    Icon(
                        Octicons.ChevronLeft24,
                        contentDescription = "",
                        modifier = m.padding(5.dp)
                    )
                }
                Card(
                    shape = RoundedCornerShape(8.dp)
                ){
                    Icon(
                        Octicons.KebabHorizontal24,
                        contentDescription = "",
                        modifier = m.padding(5.dp)
                    )
                }
            }
        }
    ){
        Column(){
            TopSection()
            BodySection()
        }
    }
}

@Composable
private fun TopSection(m: Modifier=Modifier){
    Row(
        modifier = m
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp)
    ){
        Column(
            modifier = m.coloredShadow(
                color = Color.Black,
            ),
        ){
            Card(
                shape = RoundedCornerShape(percent = 8),
                modifier = m
                    .height(120.dp)
                    .width(100.dp)
            ){
                Box(
                    modifier = m
                        .background(color = Color.Black),
                ){
                    Image(
                        painter = painterResource(id = R.drawable.dummy_doctor),
                        contentDescription = "",
                        alpha = 0.8f,
                        contentScale = ContentScale.Crop,
                        modifier = m.fillMaxSize()
                    )
                }
            }
        }
        Spacer(modifier = m.width(20.dp))
        Column() {
            Column() {
                Text(text = "Dr. Anna baker")
                Spacer(modifier = m.height(5.dp))
                Text(
                    text = "Obgyn (RS. Universitas Indonesia)",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = m.height(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Card(shape = RoundedCornerShape(8.dp)){
                    Icon(
                        Octicons.StarFill24,
                        contentDescription = "",
                        tint = Color.Yellow,
                        modifier = m.padding(5.dp)
                    )
                }
                Spacer(modifier = m.width(10.dp))
                Column() {
                    Text(text = "Rating")
                    Spacer(modifier = m.height(5.dp))
                    Text(text = "4.5 out of 5")
                }
            }
        }
    }
}

@Composable
private fun BodySection(m: Modifier = Modifier){
    Card(
        shape = RoundedCornerShape(10),
        modifier = m
            .padding(
                vertical = 10.dp,
                horizontal = 20.dp,
            )
            .fillMaxWidth()
    ){
        Column(modifier = m.padding(10.dp)) {
            Text(text = "Total Cost")
            Text(text = "Total Cost")
        }
    }
}

@Composable
@Preview
private fun PreviewPageDetailOrder(){
    PageDetailOrder()
}
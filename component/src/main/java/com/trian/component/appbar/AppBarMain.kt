package com.trian.component.appbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.component.R

@Composable
fun AppBarMain(
    modifier: Modifier=Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal= 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.logo_kopra), contentDescription = "",
            modifier = modifier.mediaQuery(
                Dimensions.Width lessThan 400.dp,
                modifier = modifier.height(80.dp).width(80.dp)
            )
        )
        Card(
            shape = CircleShape,
            modifier = modifier.mediaQuery(
                Dimensions.Width lessThan 400.dp,
                modifier = modifier.height(50.dp).width(50.dp)
            ),
            border = BorderStroke(
                width = 1.dp,
                color = LightBackground
            ),
            elevation = 0.1.dp
        ){
            Image(
                painter = painterResource(id = R.drawable.dummy_doctor), contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}
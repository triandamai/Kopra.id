package com.trian.component.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

@Composable
fun AppBarDetailStore(
    modifier:Modifier = Modifier,
    onBackPressed:()->Unit
) {
    Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
    .fillMaxWidth()
        .background(Color.White)
    .padding(20.dp),
    ){
        IconToggleButton(checked = false, onCheckedChange = {
            onBackPressed()
        }) {
            Icon(
                Octicons.ArrowLeft24,"",
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Toko Saya",style= TextStyle().mediaQuery(
                Dimensions.Width lessThan 400.dp,
                value= TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            ))

        }
    }
}
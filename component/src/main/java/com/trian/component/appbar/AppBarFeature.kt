package com.trian.component.appbar

import android.graphics.drawable.Icon
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.trian.component.R

@Composable
fun AppBarFeature(name: String, image: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {

        IconButton(
            onClick = {/*TODO*/ }) {

           Icon(Icons.Filled.ArrowBackIos, contentDescription = "Arrow")

        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = "Hello, $name !",
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(5.dp))
            //image profile
            ImageAppBar(painter = painterResource(id = R.drawable.example_profile))
        }
    }
}

@Composable
fun ImageAppBar(
    painter: Painter,
) {
    
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(end = 5.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = "profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(1.dp, Color.Gray, CircleShape)
        )

    }

}

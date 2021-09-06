package com.trian.component.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.CardColor
import com.trian.component.ui.theme.ColorFontAppBarr
import com.trian.component.ui.theme.ColorFontSw
import com.trian.component.ui.theme.TesMultiModuleTheme

/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */

@Preview
@Composable
fun MainAppBarPreview(){
    TesMultiModuleTheme() {
        AppbarMainPage(page = "Main", name = "Andi", onBackPress = {})
    }
}


@Composable
fun AppbarMainPage(page : String, name: String, onBackPress:()->Unit){

    TopAppBar(
        title = {
            Text(
                text = page,
                textAlign = TextAlign.Center,
                color = ColorFontSw,
                fontSize = 26.sp
            )
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp)
            ) {
                Text(
                    text = "Hello, $name !",
                    textAlign = TextAlign.Center,
                    color = ColorFontSw,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()

                    ) {
                        Image(
                            painter = painterResource(id = com.trian.component.R.drawable.example_profile),
                            contentDescription = "profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)                       // clip to the circle shape
                                .border(1.dp, CardColor, CircleShape)
                                .fillMaxHeight()
                                .fillMaxWidth()
                        )

                    }
                }
            }

        },
        backgroundColor = CardColor,
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    )

}
package com.trian.component.appbar

/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft16
import compose.icons.octicons.ArrowLeft24

@Preview
@Composable
fun ComposView(){
    TesMultiModuleTheme() {
        AppBarDetail(page = "Setting", onBackPress = {/*todo*/})
    }
}

@Composable
fun AppBarDetail(elevation:Dp=0.dp,page : String, onBackPress : () -> Unit ){

    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {

                Text(
                    text = page,
                    color = ColorFontFeatures,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { onBackPress}) {
                    Icon(
                        imageVector = Octicons.ArrowLeft24,
                        contentDescription = "Arrow",
                        tint = ColorFontFeatures
                    )
                }

            }

        },

        backgroundColor = Color.Transparent,
        modifier = Modifier.fillMaxWidth(),
        elevation = elevation
    )
}
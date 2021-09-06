package com.trian.component.appbar


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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.trian.component.ui.theme.CardColor
import com.trian.component.ui.theme.ColorFontAppBarr
import com.trian.component.ui.theme.ColorFontSw
import com.trian.component.ui.theme.TesMultiModuleTheme

@Preview
@Composable
fun ComposView(){
    TesMultiModuleTheme() {
        AppBarDetail(page = "Setting", onBackPress = {/*todo*/})
    }
}

@Composable
fun AppBarDetail(page : String, onBackPress : () -> Unit ){

    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {

                Text(
                    text = page,
                    color = ColorFontSw,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center

                    )
            }
        },
        navigationIcon = {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.ArrowBackIos,
                        contentDescription = "Arrow",
                        tint = ColorFontSw
                    )
                }

            }

        },

        backgroundColor = CardColor,
        modifier = Modifier.fillMaxWidth(),
        elevation = 10.dp
    )
}
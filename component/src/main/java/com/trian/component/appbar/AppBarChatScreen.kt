package com.trian.component.appbar

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.trian.component.ui.theme.GreenPrimary
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 24/10/2021
 */
@Composable
fun AppBarChatScreen(
    title:String,
    subtitle:String,
    onBackPressed:()->Unit
) {
    TopAppBar(
        backgroundColor= GreenPrimary,
        navigationIcon = {
            IconToggleButton(checked = false, onCheckedChange = {
                onBackPressed()
            }) {
                Icon(
                    imageVector = Octicons.ArrowLeft24,
                    contentDescription = "Back",
                    tint= Color.White
                )
            }
        },
        title = {
          Column(
              horizontalAlignment = Alignment.Start,
              verticalArrangement = Arrangement.Center) {
              Text(
                  title,
                  style= TextStyle(
                      fontWeight = FontWeight.SemiBold
                  ),
                  color = Color.White
              )
              Text(
                  subtitle,
                  style= TextStyle(
                      fontWeight = FontWeight.Normal
                  ),
                  color = Color.White
              )
          }
      }
    )
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
)
@Composable
fun PreviewAppBarChatScreenLight(){
    AppBarChatScreen(
        "Maju Jaya",
        "Penyewa kendaraan"
    ) {

    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewAppBarChatScreenDark(){
    AppBarChatScreen(
        "Maju Jaya",
        "Penyewa kendaraan"
    ) {

    }
}
package com.trian.component.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

@Composable
fun AppBarFormStore(
    title:String="",
    onBackPressed:()->Unit
) {
    TopAppBar(
        backgroundColor= GreenPrimary,
        navigationIcon = {
            IconToggleButton(checked = false, onCheckedChange ={
                onBackPressed()
            }) {
                Icon(
                    Octicons.ArrowLeft24,"",
                )
            }
        },
        title = {
            Text(
                text=title,
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,))
            )
        }
    )

}
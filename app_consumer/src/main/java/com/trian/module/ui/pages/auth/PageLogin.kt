package com.trian.module.ui.pages.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24


@Composable
fun PageLogin(m:Modifier = Modifier){
    Scaffold(
        topBar = {
            Icon(
                Octicons.ArrowLeft24,"",
                modifier = m.padding(10.dp),
            )
        }
    ) {
        Column(
            modifier = m.padding(10.dp),
        ) {
            Text(
                text = "Masukan nomor HP",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value=MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                )
            )
            Spacer(modifier = m.height(10.dp))
            Text(
                text = "Kami akan mengirimkan kode konfirmasi",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value=MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                )
            )

        }
    }
}

@Composable
@Preview
private fun PreviewPageLogin(){
    PageLogin()
}
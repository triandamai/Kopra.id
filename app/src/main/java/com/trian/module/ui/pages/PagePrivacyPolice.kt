package com.trian.module.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.jeziellago.compose.markdowntext.MarkdownText
import com.trian.component.datum.PrivacyPolice
import com.trian.component.ui.theme.LightBackground
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24
import compose.icons.octicons.KebabHorizontal24

@Composable
fun PagePrivacyPolice(m:Modifier=Modifier){
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = { TopBarSection() },
        backgroundColor = LightBackground
    ){
        Column(
            modifier = m.verticalScroll(state = scrollState)
        ) {
            MarkdownText(
                markdown = PrivacyPolice.markdownPrivacyPolice,
                modifier = m.padding(20.dp),
            )
        }
    }
}

@Composable
private fun TopBarSection(m:Modifier = Modifier){
    Column(
        modifier = m.padding(20.dp)
    ) {
        Row(
            modifier = m.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
            Text(text = "Syarat & Ketentuan")
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
}

@Preview
@Composable
fun PreviewPagePrivacyPolice(){
    PagePrivacyPolice()
}
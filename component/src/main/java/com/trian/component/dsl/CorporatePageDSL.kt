package com.trian.component.dsl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.trian.component.ui.theme.LightBackground

class BodyCorporateDSL{
    @Composable
    fun content(){

    }

    @Composable
    fun rightContent(){

    }
}

class CorporatePageDSL {
    @Composable
    fun sidebar(content:@Composable ()->Unit) {
        Column {
            content.invoke()
        }

    }

    @Composable
    fun body(content:@Composable BodyCorporateDSL.()->Unit) {
        Column {
            val body = BodyCorporateDSL()
            content.invoke(body)
        }

    }
}


@Composable
fun CorporatePage(
    appBar: @Composable ()->Unit,
    scaffoldState: ScaffoldState,
    content:@Composable CorporatePageDSL.()->Unit
) {
    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density

    val dt = CorporatePageDSL()
    Scaffold(
        topBar = {
            appBar()
        },
        backgroundColor = LightBackground,
        scaffoldState = scaffoldState
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            content.invoke(dt)
        }
    }
}
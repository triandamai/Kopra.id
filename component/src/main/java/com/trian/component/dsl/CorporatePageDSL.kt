package com.trian.component.dsl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    fun header(content:@Composable ()->Unit)=
        content.invoke()

    @Composable
    fun sidebar(content:@Composable ()->Unit) {
        content.invoke()
    }

    @Composable
    fun body(content:@Composable BodyCorporateDSL.()->Unit) {
       val body = BodyCorporateDSL()
        content.invoke(body)
    }
}


@Composable
fun CorporatePage(
    appBar: @Composable ()->Unit,
    scaffoldState: ScaffoldState,
    content:@Composable CorporatePageDSL.()->Unit
) {
    val dt = CorporatePageDSL()
    Scaffold(
        topBar = {
            appBar()
        },
        backgroundColor = LightBackground,
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            content.invoke(dt)
        }
    }
}
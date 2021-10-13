package com.trian.component.dsl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@DslMarker
annotation class SmartwatchUIDSL


@SmartwatchUIDSL
class DetailSmartwatchUIDSL{
    @Composable
    fun header(content:@Composable ()->Unit)=
        content.invoke()

    @Composable
    fun body(content:@Composable ()->Unit) {
        content.invoke()
    }
    @Composable
    fun footer(content:@Composable ()->Unit) {
        content.invoke()
    }

}


@Composable
fun DetailSmartwatchUI(
    appBar: @Composable ()->Unit,
    scaffoldState: ScaffoldState,
    content:@Composable DetailSmartwatchUIDSL.()->Unit
){
    val dt = DetailSmartwatchUIDSL()
    Scaffold(
        topBar = {
           appBar()
        },
        backgroundColor = Color.White
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
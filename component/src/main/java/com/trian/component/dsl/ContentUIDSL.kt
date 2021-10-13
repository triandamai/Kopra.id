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

@DslMarker
annotation class ContentDSL

@ContentDSL
class ContentUIDSL{
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
fun ContentUI(
    appBar: @Composable ()->Unit,
    scaffoldState: ScaffoldState,
    content:@Composable ContentUIDSL.()->Unit
) {
    val dt = ContentUIDSL()
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
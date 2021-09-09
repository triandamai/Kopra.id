package com.trian.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.R

/**
 * Content Permission
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */

@ExperimentalMaterialApi
@Composable
fun BottomSheetPermission(modifier: Modifier = Modifier,onAllowClicked:()->Unit){
    Column(modifier= modifier
        .fillMaxWidth()
        .padding(16.dp)
        .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cexup need some access!")
        Image(
            painter = painterResource(id = R.drawable.dummy_onboard),
            modifier = modifier.width(300.dp).height(300.dp),
            contentDescription = "Permission")
        Text(
            text = "Allow Cexup access to location,bluetooth,and media storage?",
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            softWrap = true
            )
        Button(onClick = onAllowClicked,modifier= modifier.padding(vertical = 16.dp)) {
            Text(text = "Allow All Access")
        }

    }
}



@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewBottomSheetPermission(){
    BottomSheetPermission(onAllowClicked = {})
}
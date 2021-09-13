package com.trian.component.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.ColorFontCardFeatures
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.component.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.CheckCircleFill16

/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview
@Composable
fun CardFeaturePreview(){
    TesMultiModuleTheme {
        CardFeatures(image = "", name = "Body Mask Index", onClickPressed = {})

    }
}
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun CardFeatures(
    modifier:Modifier=Modifier,
    image : String,
    name : String,
    onClickPressed: ()->Unit
){
    var visible by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .width(75.dp)
            .height(201.dp)
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
            .coloredShadow(
                color=ColorFontFeatures,
                alpha = 0.2f,
                shadowRadius = 10.dp
            ),
    ) {
       Column(modifier = modifier
           .clip(RoundedCornerShape(12.dp))
           .fillMaxWidth()
           .fillMaxHeight()
           .background(Color.White)
           .clickable {
               visible = !visible
           }
       ) {
           Column(
               modifier = Modifier
                   .padding(10.dp)
                   .background(color = Color.White)
                   .height(IntrinsicSize.Min),
           ) {
               Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(20.dp),
                   horizontalArrangement= Arrangement.Start
               ) {
                   AnimatedVisibility(
                       visible = visible,
                       enter = fadeIn(
                           // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                           initialAlpha = 0.4f
                       ),
                       exit = fadeOut(
                           // Overwrites the default animation with tween
                           animationSpec = tween(durationMillis = 250)
                       )
                   ) {
                       // Content that needs to appear/disappear goes here:
                       Icon(
                           imageVector = Octicons.CheckCircleFill16,
                           contentDescription = "",
                           modifier = Modifier.size(20.dp),
                           tint = ColorFontCardFeatures
                       )
                   }
               }
               Column(
                   modifier = Modifier
                       .padding(top = 10.dp)
                       .height(IntrinsicSize.Min)
                       .fillMaxWidth(),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Image(
                       painter = painterResource(id = R.drawable.dummy_cardfeature),
                       contentDescription ="card feature",
                       modifier = Modifier
                           .width(70.dp)
                           .height(70.dp)
                   )
               }
               Column(
                   modifier = Modifier
                       .padding(top = 16.dp)
                       .height(IntrinsicSize.Min)
                       .fillMaxWidth(),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text(
                       text = name,
                       textAlign = TextAlign.Center,
                       style= TextStyle(
                           color = Color.Black,
                           fontSize = 20.sp
                       ),
                       modifier = Modifier.fillMaxWidth(),
                   )
               }


           }
       }

    }

}

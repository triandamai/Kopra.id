package com.trian.component.bottomnavigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.CheckCircle24
import compose.icons.octicons.Package24
import compose.icons.octicons.Person24


/**
 * Bottom Navigation
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@ExperimentalAnimationApi
@Composable
fun BottomNavigationMain(modifier: Modifier=Modifier,scroll:Int){
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = scroll < 800,
        enter = slideInVertically(
            initialOffsetY = {
                with(density) {
                    40.dp.roundToPx()
                }
            },
            animationSpec = tween(
                durationMillis = 250,
                250
            )
        ),
        exit = slideOutVertically(
          animationSpec = tween(
                durationMillis = 250,
                250
            )
        )
    ) {

        Column(modifier=modifier.padding(vertical = 8.dp,horizontal = 18.dp).coloredShadow(
            color = ColorFontFeatures,
            alpha = 0.2f
        )) {
            BottomNavigation(
                backgroundColor=Color.White,
                modifier = modifier

                    .clip(RoundedCornerShape(12.dp))
                    .coloredShadow(alpha = 1f, color = ColorFontFeatures)

            ) {
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = Octicons.Package24, "")
                    },
                    label = { Text(text = "Home")},
                    selected = true,
                    onClick = {

                    },
                    alwaysShowLabel = false
                )
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector=Octicons.CheckCircle24, "")
                    },
                    label = { Text(text = "Order")},
                    selected = true,
                    onClick = {

                    },
                    alwaysShowLabel = false
                )
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector=Octicons.Person24 ,  "")
                    },


                    label = { Text(text = "Profil")},
                    selected =true,
                    onClick = {

                    },
                    alwaysShowLabel = false
                )
            }
        }


    }

}
@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewBottomNavbar(){
    BottomNavigationMain(scroll=0)
}
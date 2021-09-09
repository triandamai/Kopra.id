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
import com.trian.component.datum.listBottomNavigation
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.DarkBackground
import com.trian.component.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.*


/**
 * Bottom Navigation
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@ExperimentalAnimationApi
@Composable
fun BottomNavigationMain(
    modifier: Modifier=Modifier,
    animate:Boolean,
    page:String,
    onItemSelected:(index:Int,route:String)->Unit
){
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = animate,
        enter = slideInVertically(
            initialOffsetY = {
                with(density) {
                    -40.dp.roundToPx()
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
                listBottomNavigation.forEachIndexed {
                    index, nav ->
                    val selectedColor = when(page == nav.route){
                        true -> BluePrimary
                        else-> DarkBackground
                    }
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = nav.icon, nav.name,
                                tint = selectedColor
                            )
                        },
                        label = {
                            Text(
                                text = nav.name,
                                color = selectedColor
                            )
                        },
                        selected = page == nav.route,
                        onClick = {
                            onItemSelected(index,nav.route)
                        },
                        alwaysShowLabel = true
                    )

                }
            }
        }


    }

}
@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewBottomNavbar(){
    BottomNavigationMain(animate= false,page = "",onItemSelected = {
        index, route ->
    })
}
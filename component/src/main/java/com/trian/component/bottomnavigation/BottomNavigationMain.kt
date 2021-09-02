package com.trian.component.bottomnavigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun BottomNavigationMain(modifier: Modifier=Modifier){
    BottomAppBar(
        backgroundColor=Color.Transparent,
        elevation = 0.dp,
        cutoutShape = RoundedCornerShape(50),
        content = {
            BottomNavigation(
                modifier = modifier
                    .clip(
                        RoundedCornerShape(topStart = 12.dp,topEnd = 12.dp,
                            bottomStart = 12.dp,bottomEnd = 12.dp))
                    .padding(bottom = 6.dp),
                elevation = 1.dp,

            ) {
                BottomNavigationItem(
                    icon = {
                        Icon(Icons.Filled.Favorite , "")
                    },
                    label = { Text(text = "Favorite")},
                    selected = true,
                    onClick = {

                    },
                    alwaysShowLabel = false
                )

                BottomNavigationItem(
                    icon = {
                        Icon(Icons.Filled.Upload ,  "")
                    },


                    label = { Text(text = "Upload")},
                    selected =true,
                    onClick = {

                    },
                    alwaysShowLabel = false
                )
            }
        }
    )
}
@Preview
@Composable
fun PreviewBottomNavbar(){
    BottomNavigationMain()
}
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
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.Octicons
import compose.icons.octicons.CheckCircle24
import compose.icons.octicons.Package24
import compose.icons.octicons.Person24


/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@Composable
fun BottomNavigationMain(modifier: Modifier=Modifier){
    BottomAppBar(
        elevation = 35.dp,
        backgroundColor = Color.Transparent,
        modifier = modifier.padding(vertical = 8.dp,horizontal = 18.dp)
    ) {
        BottomNavigation(
            backgroundColor=Color.White,
            modifier = modifier

                .clip(RoundedCornerShape(12.dp))

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
@Preview
@Composable
fun PreviewBottomNavbar(){
    BottomNavigationMain()
}
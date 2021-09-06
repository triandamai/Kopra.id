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
    BottomNavigation(
        backgroundColor=Color.White,
        modifier = modifier
            .padding(horizontal = 16.dp,vertical = 8.dp).clip(RoundedCornerShape(16.dp)),
        elevation = 0.dp,

        ) {
        BottomNavigationItem(
            icon = {
                Icon(Icons.Outlined.Home , "")
            },
            label = { Text(text = "Home")},
            selected = true,
            onClick = {

            },
            alwaysShowLabel = false
        )
        BottomNavigationItem(
            icon = {
                Icon(Icons.Outlined.LibraryBooks , "")
            },
            label = { Text(text = "Order")},
            selected = true,
            onClick = {

            },
            alwaysShowLabel = false
        )
        BottomNavigationItem(
            icon = {
                Icon(Icons.Outlined.Person ,  "")
            },


            label = { Text(text = "Profil")},
            selected =true,
            onClick = {

            },
            alwaysShowLabel = false
        )
    }
}
@Preview
@Composable
fun PreviewBottomNavbar(){
    BottomNavigationMain()
}
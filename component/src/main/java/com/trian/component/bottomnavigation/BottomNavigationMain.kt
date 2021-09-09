package com.trian.component.bottomnavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
@Composable
fun BottomNavigationMain(modifier: Modifier=Modifier){
    BottomAppBar(
        elevation = 35.dp,
        backgroundColor = Color.Transparent,
        modifier = modifier
            .padding(vertical = 8.dp,horizontal = 18.dp)
    ) {
        BottomNavigation(
            backgroundColor=Color.White,
            modifier = modifier

                .clip(RoundedCornerShape(12.dp))
                .coloredShadow(color = ColorFontFeatures)

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
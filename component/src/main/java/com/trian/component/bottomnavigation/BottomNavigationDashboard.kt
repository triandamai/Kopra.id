package com.trian.component.bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.trian.component.ui.theme.GreenPrimary

/**
 * Main Page
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */
@Composable
fun BottomNavigationDashboard(
    items:List<BottomNavigationData>,
    selectedItem:String,
    onItemClick:(BottomNavigationData)->Unit,
    modifier:Modifier=Modifier
){
    BottomNavigation {
        items.forEach {
            item->
            BottomNavigationItem(
                selected = selectedItem == item.route ,
                onClick = { onItemClick(item) },
                label = {
                        Text(text = item.title)
                },
                icon = {
                       Icon(imageVector = item.icon, contentDescription = item.title)
                },
                alwaysShowLabel = true,
                modifier = modifier.background(color = Color.Black),
                selectedContentColor = GreenPrimary,
                unselectedContentColor = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomNavigationDashboard(){

}
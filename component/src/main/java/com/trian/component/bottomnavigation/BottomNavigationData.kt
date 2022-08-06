package com.trian.component.bottomnavigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.trian.component.Routes
import compose.icons.Octicons
import compose.icons.octicons.*

/**
 * Main Page
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */
sealed class BottomNavigationData (val icon:ImageVector,val title:String,val route:String,){
    object Main: BottomNavigationData(Octicons.Home16,"Home", Routes.Dashboard.HOME)
    object Chat: BottomNavigationData(Octicons.Mail16,"Chat",Routes.Dashboard.LIST_CHAT)
    object Transaction: BottomNavigationData(Octicons.PackageDependencies16,"Transaksi",Routes.Dashboard.LIST_TRANSACTION)
    object Profile: BottomNavigationData(Octicons.Person16,"Profile",Routes.Dashboard.PROFILE)
}
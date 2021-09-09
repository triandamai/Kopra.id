package com.trian.component.datum


import androidx.compose.ui.graphics.vector.ImageVector
import com.trian.common.utils.route.Routes
import compose.icons.Octicons
import compose.icons.octicons.*

data class BottomNavigationItem(
    var index:Int,
    var name:String,
    var icon: ImageVector,
    var route:String
)
val listBottomNavigation = listOf(
    BottomNavigationItem(
        0,
        "Home",
        Octicons.Package24,
        Routes.NESTED_DASHBOARD.HOME
    ),
    BottomNavigationItem(
        1,
        "Hospital",
        Octicons.Organization24,
        Routes.NESTED_DASHBOARD.RESERVATION
    ),
    BottomNavigationItem(
        2,
        "Call Doctor",
        Octicons.DeviceDesktop24,
        Routes.NESTED_DASHBOARD.CALL_DOCTOR
    ),
    BottomNavigationItem(
        3,
        "Account",
        Octicons.Person24,
        Routes.NESTED_DASHBOARD.ACCOUNT
    ),
)
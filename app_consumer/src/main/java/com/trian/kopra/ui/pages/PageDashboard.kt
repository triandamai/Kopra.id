package com.trian.kopra.ui.pages

import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.component.bottomnavigation.BottomNavigationDashboard
import com.trian.component.bottomnavigation.BottomNavigationData
import com.trian.data.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope

/**
 * Main Page
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */
@Composable
fun PageDashboard(
    modifier: Modifier = Modifier,
    page:String="",
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){

    Scaffold(
        topBar = {
            when(page){
                Routes.Dashboard.HOME->{ }
                Routes.Dashboard.LIST_CHAT->{ }
                Routes.Dashboard.LIST_TRANSACTION->{ }
                Routes.Dashboard.PROFILE->{ }
            }
        },
        bottomBar = {
           BottomNavigationDashboard(items = listOf(
               BottomNavigationData.Main,
               BottomNavigationData.Chat,
               BottomNavigationData.Transaction,
               BottomNavigationData.Profile
           ), selectedItem = page, onItemClick = {

           })
        }
    ) {
        when(page){
            Routes.Dashboard.HOME->{ }
            Routes.Dashboard.LIST_CHAT->{ }
            Routes.Dashboard.LIST_TRANSACTION->{ }
            Routes.Dashboard.PROFILE->{ }
        }
    }
}

@Preview
@Composable
fun PreviewPageDashboard(){
    PageDashboard(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope= rememberCoroutineScope()
    )
}



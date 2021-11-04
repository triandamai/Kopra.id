package com.trian.kopra.ui.pages

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarHistoryTransaction
import com.trian.component.appbar.AppBarMain
import com.trian.component.bottomnavigation.BottomNavigationDashboard
import com.trian.component.bottomnavigation.BottomNavigationData
import com.trian.data.viewmodel.MainViewModel
import com.trian.kopra.ui.pages.main.PageListTransaction
import com.trian.kopra.ui.pages.main.PageMain
import com.trian.kopra.ui.pages.main.PageProfile
import kotlinx.coroutines.CoroutineScope

/**
 * Main Page
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */
@ExperimentalPagerApi
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
                Routes.Dashboard.HOME->{ AppBarMain() }
                Routes.Dashboard.LIST_TRANSACTION->{ AppBarHistoryTransaction() }
                Routes.Dashboard.PROFILE->{ }
            }
        },
        bottomBar = {
           BottomNavigationDashboard(
               items = listOf(
                    BottomNavigationData.Main,
                    BottomNavigationData.Transaction,
                    BottomNavigationData.Profile
                ),
               selectedItem = page,
               onItemClick = {
                    navHostController.navigate(it.route){
                        launchSingleTop = true
                    }
               }
           )
        }
    ) {
        when(page){
            Routes.Dashboard.HOME->{
                PageMain(
                    mainViewModel = mainViewModel,
                    navHostController = navHostController,
                    scope = scope
                )
            }
            Routes.Dashboard.LIST_TRANSACTION->{
                PageListTransaction(
                    mainViewModel = mainViewModel,
                    navHostController = navHostController,
                    scope = scope
                )
            }
            Routes.Dashboard.PROFILE->{
                PageProfile(
                    mainViewModel = mainViewModel,
                    navHostController = navHostController,
                    scope = scope
                ){

                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewPageDashboard(){
    PageDashboard(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope= rememberCoroutineScope()
    )
}



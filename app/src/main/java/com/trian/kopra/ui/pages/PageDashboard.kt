package com.trian.kopra.ui.pages

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
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
import com.trian.component.appbar.AppBarProfile
import com.trian.component.bottomnavigation.BottomNavigationDashboard
import com.trian.component.bottomnavigation.BottomNavigationData
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.LevelUser
import com.trian.kopra.ui.pages.main.PageListTransaction
import com.trian.kopra.ui.pages.main.PageListTransactionSeller
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
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    page:String="",
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope,
    restartActivity:()->Unit
){
    var currentUser  by mainViewModel.currentUser

    LaunchedEffect(key1 =scaffoldState){
        mainViewModel.syncUser()
        mainViewModel.getCurrentUser { hasUser, user ->
            if(hasUser){
                currentUser = user
            }
        }
    }

    Scaffold(
        scaffoldState=scaffoldState,
        topBar = {
            when(page){
                Routes.Dashboard.HOME->{
                    AppBarMain(mainViewModel = mainViewModel) }
                Routes.Dashboard.LIST_TRANSACTION->{ AppBarHistoryTransaction() }
                Routes.Dashboard.PROFILE->{ AppBarProfile()}
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
                    currentUser?.let {
                        if(it.levelUser == LevelUser.COLLECTOR ||
                            it.levelUser == LevelUser.TENANT){
                            PageListTransactionSeller(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                scope = scope
                            )
                        }else{
                            PageListTransaction(
                                mainViewModel = mainViewModel,
                                navHostController = navHostController,
                                scope = scope
                            )
                        }
                    }

            }
            Routes.Dashboard.PROFILE->{
                PageProfile(
                    mainViewModel = mainViewModel,
                    navHostController = navHostController,
                    scope = scope
                ){
                    restartActivity()
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
    ){}
}



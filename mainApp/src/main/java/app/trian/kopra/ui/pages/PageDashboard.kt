package app.trian.kopra.ui.pages

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import androidx.navigation.compose.rememberNavController
import app.trian.kopra.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

import com.trian.component.appbar.AppBarHistoryTransaction
import com.trian.component.appbar.AppBarMain
import com.trian.component.appbar.AppBarProfile
import com.trian.component.bottomnavigation.BottomNavigationDashboard
import com.trian.component.bottomnavigation.BottomNavigationData

import app.trian.kopra.ui.pages.main.PageListTransaction
import app.trian.kopra.ui.pages.main.PageListTransactionSeller
import app.trian.kopra.ui.pages.main.PageMain
import app.trian.kopra.ui.pages.main.PageProfile
import com.trian.component.Routes
import com.trian.data.model.LevelUser
import com.trian.data.model.getType

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
    val ctx = LocalContext.current
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
                    AppBarMain(profilePicture = "") }
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
                when(ctx.getType()){
                    LevelUser.TENANT -> {
                        PageListTransactionSeller(
                            mainViewModel = mainViewModel,
                            navHostController = navHostController,
                            scope = scope
                        )
                    }
                    LevelUser.COLLECTOR -> {
                        PageListTransactionSeller(
                            mainViewModel = mainViewModel,
                            navHostController = navHostController,
                            scope = scope
                        )
                    }
                    LevelUser.FARMER -> {
                        PageListTransaction(
                            mainViewModel = mainViewModel,
                            navHostController = navHostController,
                            scope = scope
                        )
                    }
                    LevelUser.UNKNOWN -> {

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



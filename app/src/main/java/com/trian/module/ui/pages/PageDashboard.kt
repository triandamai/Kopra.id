package com.trian.module.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.route.Routes
import com.trian.component.bottomnavigation.BottomNavigationMain
import com.trian.component.ui.theme.LightBackground
import kotlinx.coroutines.CoroutineScope
import com.trian.component.appbar.*
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.*
import com.trian.module.ui.pages.main.DashboardListOrder
import com.trian.module.ui.pages.main.DashboardHome
import com.trian.module.ui.pages.main.DashboardListHospital
import com.trian.module.ui.pages.main.PageProfile

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 02/09/2021
 */
@ExperimentalAnimationApi
@Composable
fun PageDashboard(
    modifier:Modifier = Modifier,
    nav: NavHostController,
    scope: CoroutineScope,
    viewModel:MainViewModel,
    page:String,
    toFeature: (ServiceType) -> Unit,
    changeStatusBar:(Color)->Unit,
    openCamera: () -> Unit,
    openGallery : () -> Unit
) {
    val scrollState = rememberScrollState()
    val listState = rememberLazyListState()

    var shouldAnimateBottomNav by remember {
        mutableStateOf(true)
    }
    var shouldFloatAppBar by remember{
        mutableStateOf(true)
    }

   shouldFloatAppBar = if(scrollState.value >= 300){
        changeStatusBar(Color.White)
        true
    }else{
        if(page == Routes.NESTED_DASHBOARD.HOME) {
            changeStatusBar(LightBackground)
        }else{
            changeStatusBar(Color.White)
        }
        false
    }
   shouldAnimateBottomNav = when(page){
        Routes.NESTED_DASHBOARD.HOME->{
            scrollState.value <= 800
        }
       Routes.NESTED_DASHBOARD.LIST_HOSPITAL->{
           listState.firstVisibleItemIndex < 2
       }
       Routes.NESTED_DASHBOARD.LIST_ORDER->{
           listState.firstVisibleItemIndex < 2
       }
       Routes.NESTED_DASHBOARD.ACCOUNT->{
           listState.firstVisibleItemIndex < 2
       }
       else->true
    }

    Scaffold(
        backgroundColor = LightBackground,
        topBar = {
                 when(page){
                     Routes.NESTED_DASHBOARD.ACCOUNT->{
                         AppBarDetail(
                             page = "Account",
                             elevation = 1.dp,
                             color = Color.White
                         ) {

                         }
                     }
                     Routes.NESTED_DASHBOARD.LIST_HOSPITAL->{
                         AppBarHospital(
                             onNotification = {},
                             onHistoryClick = {},
                             onSearch = {}
                         )
                     }
                     Routes.NESTED_DASHBOARD.HOME ->{
                         AppbarDashboardHome(
                             name = "Trian Damai",
                             shouldFloating = shouldFloatAppBar
                         ) {}
                     }
                     Routes.NESTED_DASHBOARD.LIST_ORDER->{
                         APpBarListOrder(
                             shouldFloating = shouldFloatAppBar
                         )
                     }
                     else->{
                         AppbarMainPage(page = "", name = "") {

                         }
                     }
                 }
        },
        bottomBar = {
            BottomNavigationMain(
                animate = shouldAnimateBottomNav,
                page = page,
                onItemSelected = {
                    _, route ->

                    nav.navigate(route){
                        if(nav.findDestination(Routes.NESTED_DASHBOARD.HOME) != null){
                            launchSingleTop= true
                            popUpTo(
                                nav.findDestination(Routes.NESTED_DASHBOARD.HOME)!!.id
                            ){
                                inclusive=true
                            }
                        }
                        if(nav.findDestination(Routes.NESTED_DASHBOARD.ACCOUNT) != null){
                            launchSingleTop= true
                            popUpTo(
                                nav.findDestination(Routes.NESTED_DASHBOARD.ACCOUNT)!!.id
                            ){
                                inclusive=true
                            }
                        }
                        if(nav.findDestination(Routes.NESTED_DASHBOARD.LIST_HOSPITAL) != null){
                            launchSingleTop= true
                            popUpTo(
                                nav.findDestination(Routes.NESTED_DASHBOARD.LIST_HOSPITAL)!!.id
                            ){
                                inclusive=true
                            }
                        }
                        if(nav.findDestination(Routes.NESTED_DASHBOARD.LIST_ORDER) != null){
                            launchSingleTop= true
                            popUpTo(
                                nav.findDestination(Routes.NESTED_DASHBOARD.LIST_ORDER)!!.id
                            ){
                                inclusive=true
                            }
                        }

                    }

                }
            )
        }

    ) {
        when(page){
            Routes.NESTED_DASHBOARD.HOME->{
                DashboardHome(
                    scrollState=scrollState,
                    nav=nav,
                    scope = scope,
                    viewModel=viewModel,
                    toFeature = toFeature
                )
            }
            Routes.NESTED_DASHBOARD.LIST_ORDER->{
                DashboardListOrder(
                    scrollState = listState,
                    nav=nav,
                    scope=scope,
                    viewModel = viewModel
                )
            }
            Routes.NESTED_DASHBOARD.LIST_HOSPITAL->{
                DashboardListHospital(
                    scrollState = listState,
                    nav=nav,
                    scope=scope,
                    viewModel = viewModel
                )
            }
            Routes.NESTED_DASHBOARD.ACCOUNT->{
                PageProfile(
                    listState = listState,
                    scope= scope,
                    viewModel= viewModel,
                    nav=nav,
                    openGallery = {openGallery()},
                    openCamera = {openCamera()}
                )
            }
            else ->{

            }
        }
    }
}


@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewComponentDashboard() {
    PageDashboard(
        nav= rememberAnimatedNavController(),
        scope = rememberCoroutineScope(),
        viewModel= viewModel(),
        toFeature = {},
        changeStatusBar={},
        page = "",
        openCamera = {},
        openGallery = {}
    )
}


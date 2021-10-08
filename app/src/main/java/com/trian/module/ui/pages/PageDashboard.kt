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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.route.Routes
import com.trian.component.bottomnavigation.BottomNavigationMain
import com.trian.component.ui.theme.LightBackground
import kotlinx.coroutines.CoroutineScope
import com.trian.component.appbar.*
import com.trian.data.viewmodel.MainViewModel
import com.trian.data.viewmodel.TelemedicineViewModel
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
    telemedicineViewModel: TelemedicineViewModel,
    page:String,
    toFeature: (ServiceType) -> Unit,
    changeStatusBar:(Color)->Unit,
    openCamera: () -> Unit,
    openGallery : () -> Unit,
    restartActivity:()->Unit
) {
    val scrollState = rememberScrollState()
    val listState = rememberLazyListState()

    val currentUser by viewModel.user

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
        if(page == Routes.Dashboard.HOME) {
            changeStatusBar(LightBackground)
        }else{
            changeStatusBar(Color.White)
        }
        false
    }
   shouldAnimateBottomNav = when(page){
        Routes.Dashboard.HOME->{
            scrollState.value <= 800
        }
       Routes.Dashboard.LIST_HOSPITAL->{
           listState.firstVisibleItemIndex < 2
       }
       Routes.Dashboard.LIST_ORDER->{
           listState.firstVisibleItemIndex < 2
       }
       Routes.Dashboard.ACCOUNT->{
           listState.firstVisibleItemIndex < 2
       }
       else->true
    }

    Scaffold(
        backgroundColor = LightBackground,
        topBar = {
                 when(page){
                     Routes.Dashboard.ACCOUNT->{
                         AppBarDetail(
                             page = "Account",
                             elevation = 1.dp,
                             color = Color.White
                         ) {

                         }
                     }
                     Routes.Dashboard.LIST_HOSPITAL->{
                         AppBarHospital(
                             onNotification = {},
                             onHistoryClick = {},
                             onSearch = {}
                         )
                     }
                     Routes.Dashboard.HOME ->{
                         AppbarDashboardHome(
                             name = currentUser?.name ?:"",
                             shouldFloating = shouldFloatAppBar
                         ) {}
                     }
                     Routes.Dashboard.LIST_ORDER->{
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
                        popUpTo(nav.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true

                    }

                }
            )
        }

    ) {
        when(page){
            Routes.Dashboard.HOME->{
                DashboardHome(
                    scrollState=scrollState,
                    nav=nav,
                    scope = scope,
                    viewModel=viewModel,
                    toFeature = toFeature,
                    telemedicineViewModel = telemedicineViewModel
                )
            }
            Routes.Dashboard.LIST_ORDER->{
                DashboardListOrder(
                    scrollState = listState,
                    nav=nav,
                    scope=scope,
                    viewModel = viewModel
                )
            }
            Routes.Dashboard.LIST_HOSPITAL->{
                DashboardListHospital(
                    scrollState = listState,
                    nav=nav,
                    scope=scope,
                    telemedicineViewModel = telemedicineViewModel

                )
            }
            Routes.Dashboard.ACCOUNT->{
                PageProfile(
                    listState = listState,
                    scope= scope,
                    viewModel= viewModel,
                    nav=nav,
                    openGallery = {openGallery()},
                    openCamera = {openCamera()},
                    restartActivity = restartActivity
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
        openGallery = {},
        restartActivity = {},
        telemedicineViewModel = viewModel()
    )
}


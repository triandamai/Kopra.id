package com.trian.module.ui.pages

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppbarMainPage
import com.trian.component.bottomnavigation.BottomNavigationMain
import com.trian.component.ui.theme.LightBackground
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
import com.trian.component.appbar.AppBarDetail
import com.trian.component.cards.*
import com.trian.component.datum.listServices
import com.trian.domain.models.*
import com.trian.module.ui.pages.main.DashboardCallDoctor
import com.trian.module.ui.pages.main.DashboardHome
import com.trian.module.ui.pages.main.DashboardReservation

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@ExperimentalAnimationApi
@Composable
fun PageDashboard(
    modifier:Modifier = Modifier,
    nav: NavHostController,
    scope: CoroutineScope,
    page:String,
    toFeature: (ServiceType) -> Unit
) {
    var shouldAnimateBottomNav by remember {
        mutableStateOf(true)
    }
    val scrollState = rememberScrollState()
    val listState = rememberLazyListState()

   shouldAnimateBottomNav = when(page){
        Routes.NESTED_DASHBOARD.HOME->{
            scrollState.value <= 800
        }
       Routes.NESTED_DASHBOARD.RESERVATION->{
           listState.firstVisibleItemIndex < 2
       }
       Routes.NESTED_DASHBOARD.CALL_DOCTOR->{
           listState.firstVisibleItemIndex < 2
       }
       Routes.NESTED_DASHBOARD.ACCOUNT->{
           listState.firstVisibleItemIndex < 2
       }
       else->true
    }

    Scaffold(
        topBar = {
                 when(page){
                     Routes.NESTED_DASHBOARD.ACCOUNT->{
                         AppBarDetail(page = "Account") {

                         }
                     }
                     else->{
                         AppbarMainPage(page = "Dashboard", name = "Trian") {}
                     }
                 }
        },
        bottomBar = {
            BottomNavigationMain(
                animate = shouldAnimateBottomNav,
                page = page,
                onItemSelected = {
                    _, route ->
                    nav.navigate(route = route)
                }
            )
        },
        backgroundColor = LightBackground
    ) {

        when(page){
            Routes.NESTED_DASHBOARD.HOME->{
                DashboardHome(scrollState=scrollState,nav=nav,scope = scope)
            }
            Routes.NESTED_DASHBOARD.CALL_DOCTOR->{
                DashboardCallDoctor(
                    scrollState = listState,nav=nav,scope=scope
                )
            }
            Routes.NESTED_DASHBOARD.RESERVATION->{
                DashboardReservation(
                    scrollState = listState,nav=nav,scope=scope
                )
            }
            Routes.NESTED_DASHBOARD.ACCOUNT->{
                PageProfile(
                    listState = listState
                )
            }
            else ->{}
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
        toFeature = {},
        page = "")
}
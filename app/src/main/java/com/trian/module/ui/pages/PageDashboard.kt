package com.trian.module.ui.pages

import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppbarMainPage
import com.trian.component.bottomnavigation.BottomNavigationMain
import com.trian.component.ui.theme.LightBackground
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
import com.trian.component.appbar.AppBarDetail
import com.trian.component.appbar.AppBarHospital
import com.trian.component.appbar.AppbarDashboardHome
import com.trian.component.cards.*
import com.trian.component.datum.listServices
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.GrayInput
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.*
import com.trian.module.ui.pages.main.DashboardCallDoctor
import com.trian.module.ui.pages.main.DashboardHome
import com.trian.module.ui.pages.main.DashboardReservation
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft16
import compose.icons.octicons.Bell16
import compose.icons.octicons.ListUnordered16
import compose.icons.octicons.Search16

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
    opCamera: () -> Unit,
    opGallery : () -> Unit,
) {
    var shouldAnimateBottomNav by remember {
        mutableStateOf(true)
    }
    var shouldFloatAppBar by remember{
        mutableStateOf(true)
    }
    val scrollState = rememberScrollState()
    val listState = rememberLazyListState()

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
                         AppBarDetail(
                             page = "Account",
                             elevation = 1.dp,
                             color = Color.White
                         ) {

                         }
                     }
                     Routes.NESTED_DASHBOARD.RESERVATION->{

                         var query = remember { mutableStateOf("") }
                         AppBarHospital(query = query, onNotification = {}, onHistoryClick = {})
                     }
                     Routes.NESTED_DASHBOARD.HOME ->{
                         AppbarDashboardHome(
                             name = "Trian Damai",
                             shouldFloating = shouldFloatAppBar
                         ) {}
                     }
                     Routes.NESTED_DASHBOARD.CALL_DOCTOR->{
                         TopAppBar(
                             title = {
                                 Text(
                                     text = "Call Doctor",
                                     style = TextStyle(
                                         fontSize = 18.sp,
                                         fontWeight = FontWeight.SemiBold
                                     )
                                 )
                             },
                             backgroundColor = Color.White,
                             elevation = if(shouldFloatAppBar){
                                 0.dp
                             }else{
                                 3.dp
                             }
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
                            popUpTo(
                                nav.findDestination(Routes.NESTED_DASHBOARD.HOME)!!.id
                            )
                        }
                        if(nav.findDestination(Routes.NESTED_DASHBOARD.ACCOUNT) != null){
                            popUpTo(
                                nav.findDestination(Routes.NESTED_DASHBOARD.ACCOUNT)!!.id
                            )
                        }
                        if(nav.findDestination(Routes.NESTED_DASHBOARD.RESERVATION) != null){
                            popUpTo(
                                nav.findDestination(Routes.NESTED_DASHBOARD.RESERVATION)!!.id
                            )
                        }
                        if(nav.findDestination(Routes.NESTED_DASHBOARD.CALL_DOCTOR) != null){
                            popUpTo(
                                nav.findDestination(Routes.NESTED_DASHBOARD.CALL_DOCTOR)!!.id
                            )
                        }

                    }

                }
            )
        },
        backgroundColor = LightBackground
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
                    listState = listState,
                    openGallery = {opGallery()},
                    openCamera = {opCamera()}
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
        viewModel= viewModel(),
        toFeature = {},
        changeStatusBar={},
        page = "",
        opCamera = {},
        opGallery = {}
    )
}


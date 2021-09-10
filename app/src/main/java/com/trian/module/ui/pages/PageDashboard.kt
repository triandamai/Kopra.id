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
@Composable
fun DashboardHome(
    modifier:Modifier=Modifier,
    scrollState: ScrollState,
    nav: NavHostController,
    scope: CoroutineScope
){
    val stateAnimation = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = false
        }
    }
    scope.run {
        Handler(Looper.myLooper()!!).postDelayed({
            stateAnimation.targetState = true
        },500)
    }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {
        Spacer(modifier = modifier.padding(top = 16.dp))
        CardHeaderSection(title = "Health Status", moreText = "Details") {
            nav.navigate(Routes.DETAIL_HEALTH)
        }
        CardHealthStatus(state = stateAnimation)
        CardHeaderSection(title = "Services", moreText = "More") {
            nav.navigate(Routes.SHEET_SERVICE)
        }
        LazyRow(modifier = modifier.padding(vertical = 16.dp)){
            items(count=3,itemContent = {index:Int->
                CardServices(service = listServices[index], onClick ={} ,index=index)
            })
        }
        CardHeaderSection(title = "Shop", moreText = "More") {
            //to list shop/all product
        }
        LazyRow(modifier = modifier.padding(vertical = 16.dp)){
            items(count=4,itemContent = {index:Int->
                if(index == 0){
                    Spacer(modifier = Modifier.width(16.dp))
                }
                CardProduct(product = Product(
                    1,
                    1,
                    "Ini slug",
                    "Ini judul",
                    "ini description",
                    "1.200.000",
                    12,
                    1,
                    "ini Linknya gan"
                ),
                    index=index,
                    onClick ={} )
            })
        }
        CardHeaderSection(title = "News", moreText = "More") {}
        LazyRow(){
            items(count=4,itemContent = {index:Int->
                if(index == 0){
                    Spacer(modifier = Modifier.width(16.dp))
                }
                CardServices(service = Service("",R.drawable.logo_cexup), onClick ={} ,index=index)
            })
        }
        CardAppVersion()
        Spacer(modifier = modifier.height(70.dp))
    }
}

@Composable
fun DashboardReservation(
    modifier: Modifier=Modifier,
    scrollState: LazyListState,
    nav: NavHostController,
    scope: CoroutineScope
){
    LazyColumn(
        state=scrollState,
        content = {
            items(count = 10,itemContent = {
                CardHospital(
                    hospital = Hospital(
                         id=0,
                     slug="Slug",
                 description="Hospital",
                 name="RS UI ",
                 address="Jl.Meruya selatan kembangan",
                 others="others",
                 thumbOriginal="sas",
                 thumb="sas",
                    ),
                    onClick = {
                            hospital: Hospital, index: Int ->

                    })
            })
        })
}

@Composable
fun DashboardCallDoctor(
    modifier: Modifier=Modifier,
    scrollState: LazyListState,
    nav: NavHostController,
    scope: CoroutineScope
){



    LazyColumn(
        state=scrollState,
        content = {
        items(count = 10,itemContent = {
            CardOrder(
                order = Order(
                    deletedSchedule = false,
                 transactionID="XD5CF",
             hospital="RSUI",
             doctorHospitalID=0,
             address="Jl.Meruya selatan kembangan",
             doctor="Dr. Yakob togar",
             doctorSlug="yakob",
             speciality="Kandungan",
             patient= "Zidni Mujib",
             patientID= 0,
             note= "belum ada note",
             doctorNote= "",
             prescription= "",
             provisional= "",
             date= "",
             estimate= "",
             type= "",
             price= "",
             requestReschedulePatient=false,
             requestRescheduleDoctor=false,
             statusOrder= 0,
             paid= false,
             refund =false,
             bankName= "",
             accountNumber= "",
             accountName= "",
             start= "",
             join = null,
             paymentToken= "",
             allowed=false,
             requestAccess=false,
             thumb= ""
                ), 
                onClick = {
                order: Order, index: Int -> 
                
            })
        })
    })

}
@Composable
fun DashboardAccount(){}

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
package com.trian.module.ui.pages

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import com.trian.domain.models.Service
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
import com.trian.component.cards.*
import com.trian.domain.models.Product
import com.trian.domain.models.ServiceType
import com.trian.component.datum.listServices

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@ExperimentalAnimationApi
@Composable
fun PageDashboard(modifier:Modifier = Modifier,nav: NavHostController, scope: CoroutineScope, toFeature: (ServiceType) -> Unit) {

    val stateAnimation = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = false
        }
    }
    val scrollState = rememberScrollState()
    scope.run {
        Handler(Looper.myLooper()!!).postDelayed({
            stateAnimation.targetState = true
        },800)
    }

    Scaffold(
        topBar = { AppbarMainPage(page = "", name = "") {} },
        bottomBar = {
            BottomNavigationMain(scroll = scrollState.value)
        },
        backgroundColor = LightBackground
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(scrollState),
        ) {
            Spacer(modifier = modifier.padding(top = 16.dp))
            CardHeaderSection(title = "Health Status", moreText = "Details") {
                nav.navigate(Routes.DETAIl_HEALTH.name)
            }
            CardHealthStatus(state = stateAnimation)
            CardHeaderSection(title = "Services", moreText = "More") {
                nav.navigate(Routes.SHEET_SERVICE.name)
            }
            LazyRow(modifier = modifier.padding(vertical = 16.dp)){
                items(count=3,itemContent = {index:Int->
                    if(index == 0){
                        Spacer(modifier = Modifier.width(16.dp))
                    }
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
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewComponentDashboard() {
    PageDashboard(nav= rememberAnimatedNavController(),scope = rememberCoroutineScope(),toFeature = {})
}
package com.trian.module.ui.pages.main

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
import com.trian.component.cards.*
import com.trian.component.datum.listServices
import com.trian.domain.models.Product
import com.trian.domain.models.Service
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
/**
 * Dashboard Dashboard
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 10/09/2021
 */
@ExperimentalAnimationApi
@Composable
fun DashboardHome(
    modifier: Modifier =Modifier,
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

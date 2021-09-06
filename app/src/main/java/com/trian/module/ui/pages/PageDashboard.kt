package com.trian.module.ui.pages

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trian.component.appbar.AppbarMainPage
import com.trian.component.bottomnavigation.BottomNavigationMain
import com.trian.component.cards.CardHeaderSection
import com.trian.component.cards.CardHealthStatus
import com.trian.component.cards.CardServices
import com.trian.component.ui.theme.ColorBackground
import com.trian.domain.models.Service
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 02/09/2021
 */
@ExperimentalAnimationApi
@Composable
fun PageDashboard(nav: NavHostController, scope: CoroutineScope, toFeature: () -> Unit) {
    ComponentDashboard(onNavigate = { /*TODO*/ },scope = scope)
}

@ExperimentalAnimationApi
@Composable
fun ComponentDashboard(onNavigate: () -> Unit,scope: CoroutineScope, modifier: Modifier = Modifier) {
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = false
        }
    }
    scope.run {
        Handler(Looper.myLooper()!!).postDelayed({
                state.targetState = true
        },800)
    }
    Scaffold(
        topBar = { AppbarMainPage(page = "", name = "") {} },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {},
            ) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomNavigationMain()
        },
        backgroundColor = ColorBackground
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            CardHeaderSection(title = "Health Status", moreText = "Details") {}
               CardHealthStatus(state = state)

            CardHeaderSection(title = "Services", moreText = "More") {}
            LazyRow(){
                items(count=4,itemContent = {index:Int->
                    if(index == 0){
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    CardServices(service = Service("",R.drawable.logo_cexup), onClick ={} )
                })
            }
            CardHeaderSection(title = "Shop", moreText = "More") {}
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewComponentDashboard() {
    ComponentDashboard(onNavigate = { /*TODO*/ },scope = rememberCoroutineScope())
}
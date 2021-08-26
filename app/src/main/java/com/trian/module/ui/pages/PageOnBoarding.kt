package com.trian.module.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.common.utils.route.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun PageOnBoarding(nav: NavHostController,scope:CoroutineScope,modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = 3)
    fun move(index:Int){
        scope.launch {
            pagerState.animateScrollToPage(index)
        }
    }
    Scaffold(modifier = modifier.fillMaxHeight().fillMaxWidth()) {
        HorizontalPager(state = pagerState) {
            page: Int ->

            when(page){
                0 -> Column(modifier = modifier.fillMaxHeight().fillMaxWidth()) {
                    Text(text = "Page 1")
                }
                1 -> Column(modifier = modifier.fillMaxHeight().fillMaxWidth()) {
                    Text(text = "Page 2")
                }
                2 -> Column(modifier = modifier.fillMaxHeight().fillMaxWidth()) {
                    Text(text = "Page 3")
                    Button(onClick = { nav.navigate(Routes.LOGIN.name) }) {
                        Text(text = "Login")
                    }
                }
                else -> Column(modifier = modifier.fillMaxHeight().fillMaxWidth()) {
                    Text(text = "Page Else")
                    Button(onClick = { nav.navigate(Routes.LOGIN.name) }) {
                        Text(text = "Login")
                    }
                }
            }
        }
    }
}
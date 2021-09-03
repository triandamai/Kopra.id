package com.trian.module.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.common.utils.route.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun PageOnBoarding(nav: NavHostController,scope:CoroutineScope,page:Int=0) {
   ComponentOnBoarding(scope = scope, onNavigate = {
       nav.navigate(it)
   })
}

@ExperimentalPagerApi
@Composable
fun ComponentOnBoarding(scope:CoroutineScope,onNavigate:(String)->Unit,modifier: Modifier = Modifier){
    val pagerState = rememberPagerState(pageCount = 3)
    fun move(index:Int){
        scope.launch {
            pagerState.animateScrollToPage(index)
        }
    }
    Scaffold(modifier = modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        HorizontalPager(state = pagerState) {
                page: Int ->
            when(page){
                0 -> OnBoardPage(modifier,page ,onNavigate)

                1 ->  OnBoardPage(modifier,page ,onNavigate)

                2 -> OnBoardPage(modifier,page,onNavigate )

                else -> OnBoardPage(modifier,page,onNavigate)
            }
        }
    }
}

@Composable
fun OnBoardPage(modifier: Modifier,page:Int=0,onNavigate: (String) -> Unit){
    Scaffold {
        Column(modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
        ,horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Page 1")
            if(page == 2){
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Logn")
                }

            }
        }
    }
}


@ExperimentalPagerApi
@Preview
@Composable
fun PreviewComponentOnBoarding(){
    ComponentOnBoarding(scope = rememberCoroutineScope(), onNavigate ={})
}
package com.trian.kopra.ui.pages.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.component.appbar.TabLayout
import com.trian.component.cards.CardItemTransaction
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.ChatItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Page Dashboard List Transaction
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */

@ExperimentalPagerApi
@Composable
fun PageListTransaction(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope
){

    val tabData = listOf(
        "Dalam Proses",
        "Selesai"
    )
    var pagerState = rememberPagerState(pageCount = 2)

    fun onPageSwipe(index:Int){
        scope.launch {
            pagerState.animateScrollToPage(page = index)
        }
    }
        Column {
            TabLayout(
                tabItems = tabData,
                selectedTab=pagerState.currentPage,
                onTabSelected = {
                    onPageSwipe(it)
                }
            )
            HorizontalPager(state = pagerState) {
                LazyColumn(content = {
                    items(count = 10,itemContent = {
                        CardItemTransaction(chat = ChatItem(0, "", "", ""), onClick = {
                            index, chat ->
                        })
                    })
                })
            }
        }

}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewPageListTransaction(){
    PageListTransaction(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope() )
}
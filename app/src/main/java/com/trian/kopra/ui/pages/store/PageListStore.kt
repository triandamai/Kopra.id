package com.trian.kopra.ui.pages.store

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.TabLayout
import com.trian.component.cards.CardItemTransaction
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Page Dashboard Profile
 * Author Trian damai
 * Created by Trian Damai
 * 07/11/2021
 */

@ExperimentalPagerApi
@Composable
fun PageListStore(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope
){
    val tabData = listOf(
        "Penyewa",
        "Pengepul"
    )
    var pagerState = rememberPagerState(pageCount = 2)

    fun onPageSwipe(index:Int){
        scope.launch {
            pagerState.animateScrollToPage(page = index)
        }
    }
   Scaffold(
       scaffoldState =scaffoldState,
       topBar = {}
   ) {
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
                       CardItemTransaction(transaction = Transaction(),
                           onChatSender={
                                   index, transaction ->
                               navHostController.navigate(Routes.CHATSCREEN)

                           },
                           onClick = {
                                   index, chat ->
                           }
                       )
                   })
               })
           }
       }
   }
}
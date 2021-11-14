package com.trian.kopra.ui.pages.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.TabLayout
import com.trian.component.cards.CardItemTransaction
import com.trian.component.cards.CardStore
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.ChatItem
import com.trian.domain.models.StatusTransaction
import com.trian.domain.models.Store
import com.trian.domain.models.Transaction
import com.trian.domain.models.network.GetStatus
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

    val listFinishTransaction by mainViewModel.listTransactionFinished.observeAsState(initial = emptyList<Transaction>().toMutableList())
    val listUnFinishTransaction by mainViewModel.listTransactionUnFinished.observeAsState(initial = emptyList<Transaction>().toMutableList())


    fun onPageSwipe(index:Int){
        scope.launch {
            pagerState.animateScrollToPage(page = index)
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.getListTransaction{

        }
    })
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

                                    items(
                                        count = when (pagerState.currentPage) {
                                        0 -> listUnFinishTransaction.size
                                        else -> listFinishTransaction.size
                                    },
                                        itemContent = { index ->
                                        CardItemTransaction(
                                            transaction = when(pagerState.currentPage){
                                                0-> listUnFinishTransaction[index]
                                                else->listFinishTransaction[index]
                                            },
                                            index = index,
                                            onChatSender = { index, transaction ->
                                                navHostController.navigate("${Routes.CHATSCREEN}/${transaction.uid}")

                                            },
                                            onClick = { index, chat ->
                                                navHostController.navigate("${Routes.DETAIL_ORDER}/${chat.uid}")
                                            }
                                        )

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
        scope = rememberCoroutineScope()
    )
}
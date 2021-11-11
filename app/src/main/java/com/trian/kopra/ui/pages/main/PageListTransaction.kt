package com.trian.kopra.ui.pages.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

    val listTransaction by mainViewModel.listTransaction

    fun onPageSwipe(index:Int){
        scope.launch {
            pagerState.animateScrollToPage(page = index)
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.getListTransaction()
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
                when(listTransaction){
                    is GetStatus.HasData -> {
                        LazyColumn(content = {
                            items(count = listTransaction.data?.size ?: 0,itemContent = {
                                index->
                                CardItemTransaction(
                                    transaction = listTransaction.data!![index],
                                    index=index,
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
                    is GetStatus.Idle -> {

                    }
                    is GetStatus.Loading -> {

                    }
                    is GetStatus.NoData -> {

                    }
                }
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
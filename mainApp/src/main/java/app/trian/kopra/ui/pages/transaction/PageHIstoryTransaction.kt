package app.trian.kopra.ui.pages.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.component.appbar.AppBarHistoryTransaction
import com.trian.component.appbar.TabLayout
import com.trian.data.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 24/10/2021
 */
@ExperimentalPagerApi
@Composable
fun PageHistoryTransaction(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
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

    Scaffold(
        topBar = {
            AppBarHistoryTransaction()
        }
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

            }
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewPageHistoryTransaction(){
    PageHistoryTransaction(
        mainViewModel=viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope()
    )
}
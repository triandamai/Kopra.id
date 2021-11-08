package com.trian.kopra.ui.pages.store

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarListStore
import com.trian.component.appbar.TabLayout
import com.trian.component.cards.CardStore
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.Store
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

    val listCollector by mainViewModel.listCollector
    val listTenant by mainViewModel.listTenant
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
    
    LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getListCollectorStore()
        mainViewModel.getListTenantStore()
    }
   Scaffold(
       scaffoldState =scaffoldState,
       topBar = {
           AppBarListStore(onBackPressed = {
               navHostController.popBackStack()
           }) {

           }
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
               LazyColumn(
                   modifier=modifier.padding(horizontal = 16.dp),
                   content = {
                   when(pagerState.currentPage){
                       0-> {
                           items(count = listCollector.data?.size ?: 0,itemContent = {
                                   index->
                               if(index ==0){
                                   Spacer(modifier = modifier.height(10.dp))
                               }
                               CardStore(index = index,store = listCollector.data!![index],onDetail = {
                                       index, store ->
                                   navHostController.navigate("${Routes.DETAIL_TOKO}/${store.uid}")
                               },onEdit = {
                                       index, store ->

                               },onDelete = {
                                       index, store ->
                               })
                           })
                       }
                       else->{
                           items(count = listTenant.data?.size ?: 0,itemContent = {
                                   index->
                               if(index ==0){
                                   Spacer(modifier = modifier.height(10.dp))
                               }
                               CardStore(index = index,store = listTenant.data!![index],onDetail = {
                                       index, store ->
                                   navHostController.navigate("${Routes.DETAIL_TOKO}/${store.uid}")
                               },onEdit = {
                                       index, store ->

                               },onDelete = {
                                       index, store ->
                               })
                           })
                       }
                   }
               })
           }
       }
   }
}
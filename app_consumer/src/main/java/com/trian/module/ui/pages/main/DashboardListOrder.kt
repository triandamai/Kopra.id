package com.trian.module.ui.pages.main

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.route.Routes
import com.trian.component.cards.CardNotFound
import com.trian.component.cards.CardOrder
import com.trian.data.viewmodel.MainViewModel
import com.trian.data.viewmodel.TelemedicineViewModel
import com.trian.domain.models.Order
import kotlinx.coroutines.CoroutineScope
/**
 * Dashboard Call Doctor
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 10/09/2021
 */

@Composable
fun DashboardListOrder(
    modifier: Modifier =Modifier,
    scrollState: LazyListState,
    viewModel: MainViewModel,
    nav: NavHostController,
    scope: CoroutineScope,
    telemedicineViewModel: TelemedicineViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
){

    val listOrder by telemedicineViewModel.listOrderStatus.observeAsState()

    LaunchedEffect(key1 = scaffoldState) {
        telemedicineViewModel.listOrder({})
    }

    LazyColumn(
        state=scrollState,
        content = {
            item {
                Text(
                    text = "Your List Order",
                    style= TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier=modifier
                        .padding(start=16.dp,end = 16.dp,top = 16.dp,bottom = 8.dp)
                )
            }
            when(listOrder){
                is DataStatus.HasData->{
                    items(count = listOrder?.data!!.size,
                        itemContent = {index->
                            CardOrder(
                             order = listOrder?.data!![index], index=index,
                                onClick = {
                                 index: Int ->
                                nav.navigate(Routes.DETAIL_ORDER)
                            })
                        })
                }
                is DataStatus.NoData->{
                    item {
                        CardNotFound()
                    }
                }
                is DataStatus.Loading->{
                    //Loading
                }
            }
    })

}

@ExperimentalAnimationApi
@Composable
@Preview
private fun PreviewDashboardCallDoctor(){
    DashboardListOrder(
        scrollState = rememberLazyListState(),
        nav = rememberAnimatedNavController(),
        scope = rememberCoroutineScope(),
        viewModel = viewModel(),
        telemedicineViewModel = viewModel()
    )
}
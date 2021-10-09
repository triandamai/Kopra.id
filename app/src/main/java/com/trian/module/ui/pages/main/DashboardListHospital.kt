package com.trian.module.ui.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.network.NetworkStatus
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.cards.CardHospital2
import com.trian.component.cards.CardNotFound
import com.trian.component.cards.previewNotFound
import com.trian.data.viewmodel.MainViewModel
import com.trian.data.viewmodel.TelemedicineViewModel
import com.trian.domain.models.Hospital
import kotlinx.coroutines.CoroutineScope

/**
 * Dashboard Reservation
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 11/09/2021
 */
@Composable
fun DashboardListHospital(
    modifier: Modifier =Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    scrollState: LazyListState,
    nav: NavHostController,
    scope: CoroutineScope,
    telemedicineViewModel: TelemedicineViewModel
){


    val hospitals by telemedicineViewModel.hospitalStatus.observeAsState()

    LaunchedEffect(key1 = scaffoldState){
        telemedicineViewModel.hospital {  }
    }


                LazyColumn(
                    state=scrollState,
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    contentPadding = PaddingValues( vertical = 8.dp),
                    content = {
                        when(hospitals){
                            is DataStatus.NoData -> {
                                item {
                                    CardNotFound()
                                }
                            }
                            is DataStatus.Loading -> {
                                //should show loading
                            }
                            is DataStatus.HasData -> {
                                items(count = hospitals?.data!!.size,itemContent = { index->
                                    CardHospital2(
                                        hospital = hospitals?.data!![index],onClick = {
                                                _, _ ->
                                        })
                                })
                            }
                            null -> item {
                                CardNotFound()
                            }
                        }

                    })


}

@Preview
@Composable
fun PreviewDashboardReservation(){
    DashboardListHospital(
        scrollState = rememberLazyListState(),
        nav = rememberNavController() ,
        scope = rememberCoroutineScope(),
        telemedicineViewModel = viewModel()
    )
}
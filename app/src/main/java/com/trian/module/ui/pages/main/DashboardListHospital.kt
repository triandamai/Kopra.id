package com.trian.module.ui.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.cards.CardHospital2
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
    scrollState: LazyListState,
    nav: NavHostController,
    scope: CoroutineScope,
    telemedicineViewModel: TelemedicineViewModel
){

    val hospital by telemedicineViewModel.hospital

    SideEffect {
        telemedicineViewModel.hospital {  }
    }

       LazyColumn(
           state=scrollState,
           verticalArrangement = Arrangement.spacedBy(3.dp),
           contentPadding = PaddingValues( vertical = 8.dp),
           content = {
               items(count = hospital!!.size,itemContent = { index->
                   CardHospital2(
                       hospital = hospital!![index],onClick = {hospital, index ->
                   })
               })
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
package com.trian.module.ui.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
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

       LazyColumn(
           state=scrollState,
           verticalArrangement = Arrangement.spacedBy(3.dp),
           contentPadding = PaddingValues( vertical = 8.dp),
           content = {
               items(count = hospital!!.size,itemContent = { index->
                   CardHospital2(hospital =
                    Hospital(
                       id = hospital!![index].id,
                       slug = hospital!![index].slug,
                       description = hospital!![index].description,
                       thumb = hospital!![index].thumb,
                       thumb_original = hospital!![index].thumb_original,
                       name = hospital!![index].name,
                       address = hospital!![index].address,
                       others = hospital!![index].others),
                       hospitalPict = painterResource(id = R.drawable.hospital),
                       onClick = {hospital, index ->
                           nav.navigate(Routes.DETAIL_HOSPITAL)
                       }
                   )
//                   CardHospital(
//                       hospital = Hospital(
//                           id=0,
//                           slug="Slug",
//                           description="Hospital",
//                           name="RS UI ",
//                           address="Jl.Meruya selatan kembangan",
//                           others="others",
//                           thumbOriginal="sas",
//                           thumb="sas",
//                       ),
//                       onClick = {
//                               hospital: Hospital, index: Int ->
//                                nav.navigate(Routes.DETAIL_HOSPITAL)
//                       })
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
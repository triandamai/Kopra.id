package com.trian.module.ui.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.cards.CardHospital
import com.trian.component.cards.HospitalCard
import com.trian.domain.models.Hospital
import kotlinx.coroutines.CoroutineScope

/**
 * Dashboard Reservation
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 11/09/2021
 */
@Composable
fun DashboardReservation(
    modifier: Modifier =Modifier,
    scrollState: LazyListState,
    nav: NavHostController,
    scope: CoroutineScope
){

       LazyColumn(
           state=scrollState,
           verticalArrangement = Arrangement.spacedBy(12.dp),
           contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
           content = {
               items(count = 10,itemContent = {
                   HospitalCard(
                       hospitalPict = painterResource(id = R.drawable.hospital),
                       contentDescription = "",
                       hospital = Hospital(
                           id = 1,
                           slug = "rs-tele-cexup",
                           description = "",
                           thumb = "",
                           thumbOriginal = "",
                           name = "RS Tele Cexup",
                           address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
                           others = "",
                       ),
                       onClick = {hospital, index ->  },
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
    DashboardReservation(
        scrollState = rememberLazyListState(),
        nav = rememberNavController() ,
        scope = rememberCoroutineScope()
    )
}
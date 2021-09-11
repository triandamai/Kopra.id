package com.trian.module.ui.pages.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.trian.component.R
import com.trian.component.cards.CardHospital
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
        content = {
            items(count = 10,itemContent = {
                CardHospital(
                    hospital = Hospital(
                         id=0,
                     slug="Slug",
                 description="Hospital",
                 name="RS UI ",
                 address="Jl.Meruya selatan kembangan",
                 others="others",
                 thumbOriginal="sas",
                 thumb="sas",
                    ),
                    onClick = {
                            hospital: Hospital, index: Int ->

                    })
            })
        })
}
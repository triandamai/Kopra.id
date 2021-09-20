package com.trian.module.ui.pages.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.cards.CardOrder
import com.trian.domain.models.Order
import kotlinx.coroutines.CoroutineScope
/**
 * Dashboard Call Doctor
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 10/09/2021
 */

@Composable
fun DashboardCallDoctor(
    modifier: Modifier =Modifier,
    scrollState: LazyListState,
    nav: NavHostController,
    scope: CoroutineScope
){
    LazyColumn(
        state=scrollState,
        content = {
            item {
                Text(
                    text = "Your Order List",
                    style= TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier=modifier
                        .padding(start=16.dp,end = 16.dp,top = 16.dp,bottom = 8.dp)
                )
            }
        items(count = 3,itemContent = {
            CardOrder(
             order = Order(
             deletedSchedule = false,
             transactionID="XD5CF",
             hospital="RSUI",
             doctorHospitalID=0,
             address="Jl.Meruya selatan kembangan",
             doctor="Dr. Yakob togar",
             doctorSlug="yakob",
             speciality="Kandungan",
             patient= "Zidni Mujib",
             patientID= 0,
             note= "belum ada note",
             doctorNote= "",
             prescription= "",
             provisional= "",
             date= "",
             estimate= "",
             type= "",
             price= "",
             requestReschedulePatient=false,
             requestRescheduleDoctor=false,
             statusOrder= 0,
             paid= false,
             refund =false,
             bankName= "",
             accountNumber= "",
             accountName= "",
             start= "",
             join = null,
             paymentToken= "",
             allowed=false,
             requestAccess=false,
             thumb= ""
             ), index=0,
                onClick = {
                 index: Int ->
                nav.navigate(Routes.DETAIL_DOCTOR)
            })
        })
    })

}

@ExperimentalAnimationApi
@Composable
@Preview
private fun PreviewDashboardCallDoctor(){
    DashboardCallDoctor(scrollState = rememberLazyListState(), nav = rememberAnimatedNavController(), scope = rememberCoroutineScope())
}
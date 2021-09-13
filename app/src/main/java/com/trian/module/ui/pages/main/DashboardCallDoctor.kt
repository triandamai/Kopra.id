package com.trian.module.ui.pages.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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
        items(count = 10,itemContent = {
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
                ), 
                onClick = {
                order: Order, index: Int -> 
                
            })
        })
    })

}
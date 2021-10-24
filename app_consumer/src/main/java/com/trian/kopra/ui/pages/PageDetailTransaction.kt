package com.trian.kopra.ui.pages

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.trian.component.appbar.AppBarDetailTransaction
import com.trian.data.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 24/10/2021
 */
@Composable
fun PageDetailTransaction(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope
){
    Scaffold(topBar = {
        AppBarDetailTransaction {

        }
    }) {

    }
}
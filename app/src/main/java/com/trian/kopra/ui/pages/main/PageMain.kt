package com.trian.kopra.ui.pages.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.cards.HeaderDashboard
import com.trian.data.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope

/**
 * Page Dashboard Main
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */

@Composable
fun PageMain(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope
){
    var currentUser by mainViewModel.currentUser
    LaunchedEffect(key1 =scaffoldState){
        mainViewModel.getCurrentUser(){
                success,user->
            if(success){
                currentUser = user
            }

        }
    }
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        HeaderDashboard(
            displayName = currentUser?.fullName?:""
        )
    }
}

@Preview
@Composable
fun PreviewPageMain(){
    PageMain(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope() )
}
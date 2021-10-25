package com.trian.kopra.ui.pages.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.data.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope

/**
 * Page Dashboard Profile
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */

@Composable
fun PageProfile(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope
){

}

@Preview
@Composable
fun PreviewPageProfile(){
    PageProfile(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope() )
}
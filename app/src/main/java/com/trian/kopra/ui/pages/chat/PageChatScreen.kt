package com.trian.kopra.ui.pages.chat

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.appbar.AppBarChatScreen
import com.trian.component.appbar.ChatEntry
import com.trian.data.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope

/**
 * Persistence Class
 * Author Trian Damai
 * Created by Trian Damai
 * 30/10/2021
 */
@Composable
fun PageChatScreen (
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    listState: LazyListState = rememberLazyListState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){
        Scaffold(
            topBar ={
                AppBarChatScreen(title = "Toko Maju Jaya", subtitle = "Penyewa Kendaraan") {

                }
            },
            bottomBar = {
                ChatEntry {

                }
            }
        ) {
            LazyColumn(
                state = listState,
                content = {

                }
            )
        }
}


@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewPageChatScreenLight(){
    PageChatScreen(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope()
    )
}
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPageChatScreenDark(){
    PageChatScreen(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope()
    )
}

package com.trian.kopra.ui.pages.chat

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.appbar.AppBarChatScreen
import com.trian.component.appbar.ChatEntry
import com.trian.component.cards.CardItemChat
import com.trian.component.ui.theme.LightBackground
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.ChatItem
import com.trian.domain.models.network.GetStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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

    val transactionId:String = (navHostController.currentBackStackEntry?.arguments?.getString("slug") ?: "")
    val detailTransaction by mainViewModel.detailTransaction.observeAsState(initial = GetStatus.Loading())
    var currentUser by mainViewModel.currentUser
    val chats by mainViewModel.messages.observeAsState(
        initial  = emptyList<ChatItem>().toMutableList()
    )

    LaunchedEffect(Unit){
        mainViewModel.getCurrentUser { hasUser, user ->
            currentUser = user
        }
        mainViewModel.getDetailTransaction(transactionId){

        }
        mainViewModel.getChat(transactionId){
            scope.launch {
                listState.animateScrollToItem(chats.size)
            }
        }

        scope.launch {
            listState.animateScrollToItem(chats.size)
        }

    }



        Scaffold(
            topBar ={
                AppBarChatScreen(
                    title = when(detailTransaction){
                        is GetStatus.HasData -> {
                            detailTransaction.data?.store?.storeName ?: ""
                        }
                        else ->{
                            ""
                        }
                    },
                    subtitle = when(detailTransaction){
                        is GetStatus.HasData -> {
                            detailTransaction.data?.store?.phoneNumber ?: ""
                        }
                        else ->{
                            ""
                        }
                    }
                ) {

                }
            },
            bottomBar = {
                ChatEntry {
                    when(detailTransaction){
                        is GetStatus.HasData -> {
                            mainViewModel.sendChat(it,detailTransaction.data!!){
                                success: Boolean ->
                                scope.launch {
                                    listState.animateScrollToItem(chats.size)
                                }
                            }
                        }
                        is GetStatus.Idle -> {}
                        is GetStatus.Loading -> {}
                        is GetStatus.NoData -> {}
                    }

                }
            }
        ) {
            LazyColumn(
                modifier= modifier
                    .background(LightBackground)
                    .fillMaxSize(),
                state = listState,
                content = {
                    items(count = chats.size,itemContent = {
                        index: Int ->

                        val chat = chats[index]
                        CardItemChat(
                            chat =chat,
                            currentUser = currentUser!!,
                            onClick = {
                                index, chat ->
                            }
                        )

                    })
                    item {
                        Spacer(modifier = modifier.height(60.dp))
                    }
                }
            )
        }
}


@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Composable
fun PreviewPageChatScreenLight(){
    PageChatScreen(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope()
    )
}
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun PreviewPageChatScreenDark(){
    PageChatScreen(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope()
    )
}

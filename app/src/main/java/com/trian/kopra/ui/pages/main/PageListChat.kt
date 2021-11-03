package com.trian.kopra.ui.pages.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.cards.CardItemChat
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.ChatItem
import kotlinx.coroutines.CoroutineScope

/**
 * Page Dashboard List Chat
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */

@Composable
fun PageListChat(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope
){
    LazyColumn(content = {
        items(count = 10,itemContent = {
            CardItemChat(chat = ChatItem("0", 100, 100,""),  onClick = {

                index, chat ->  
            })
        })
    })
}

@Preview
@Composable
fun PreviewPageListChat(){
    PageListChat(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope() )
}
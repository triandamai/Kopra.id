package com.trian.kopra.ui.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

/**
 * Chat Screeen
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */
@Composable
fun PageChatScreen(){
    Scaffold(
        topBar = {},
        bottomBar = {}
    ) {
        LazyColumn(content = {
            items(count = 0,itemContent = {

            })
        })
    }
}

@Composable
fun PreviewPageChatScreen(){

}
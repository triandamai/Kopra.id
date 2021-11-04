package com.trian.component.cards

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.trian.component.R
import com.trian.domain.models.ChatItem

/**
 * Page Dashboard List Chat
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */

@Composable
fun CardItemChat(
    modifier: Modifier=Modifier,
    index:Int=0,
    chat:ChatItem,
    onClick:(index:Int,chat:ChatItem)-> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
            Text("Ini adalah pesan terakhir dari pengirim pesan ")
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewCardItemChatLight(){
    CardItemChat(chat = ChatItem(), onClick = {
        index, chat ->
    })
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCardItemChatDark(){
    CardItemChat(chat = ChatItem(), onClick = {
            index, chat ->
    })
}
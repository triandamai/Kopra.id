package com.trian.component.cards

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
    Row {
        Image(painter = painterResource(id = R.drawable.dummy_profile), contentDescription = "Sender Picture")
        Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Top) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
                Text("Trian Damai")
                Text("09 Oktober 2021")
            }
            Text("Ini adalah pesan terakhir dari pengirim pesan ")
        }
    }
}

@Preview
@Composable
fun PreviewCardItemChat(){

}
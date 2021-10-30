package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.R
import com.trian.domain.models.ChatItemModel
import com.trian.domain.models.Transaction

/**
 * Page Dashboard List Chat
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */

@Composable
fun CardItemTransaction(
    modifier: Modifier=Modifier,
    index:Int=0,
    chat:Transaction,
    onClick:(index:Int,chat:ChatItemModel)-> Unit
){
    Row {
        Image(
            modifier = modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            painter = painterResource(id = R.drawable.dummy_profile),
            contentDescription = "Sender Picture"
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Trian Damai")
                Text("09 Oktober 2021")
            }
            Text("Ini adalah pesan terakhir dari pengirim pesan ")
        }
    }
}

@Preview
@Composable
fun PreviewCardItemChatTransaction(){
        CardItemTransaction(chat = Transaction(), onClick = {
            index, chat ->
        })
}
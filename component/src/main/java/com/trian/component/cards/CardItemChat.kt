package com.trian.component.cards

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.common.utils.utils.coloredShadow
import com.trian.common.utils.utils.formatReadableDate
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.domain.models.ChatItem
import com.trian.domain.models.User

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
    currentUser: User,
    onClick:(index:Int,chat:ChatItem)-> Unit
){
    val owner = chat.fromUid == currentUser.uid
    Row(
        modifier=modifier

            .fillMaxWidth()
            .padding(
            vertical = 8.dp,
            horizontal = 8.dp
        ),
        horizontalArrangement = when(owner){
            true ->  Arrangement.End
            else -> Arrangement.Start
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = when(owner){
                true ->  Alignment.Start
                else -> Alignment.End
            }
        ) {
            Column(
                modifier= modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text=chat.message,
                    style = TextStyle(
                        fontSize = 18.sp
                    )
                )
            }
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = chat.createdAt.formatReadableDate(),
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }

    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewCardItemChatLight(){
    CardItemChat(
        chat = ChatItem(fromUid = "kanan"),
        currentUser = User(),
        onClick = {
            index, chat ->
        })
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCardItemChatDark(){
    CardItemChat(
        chat = ChatItem(fromUid = "kanan"),
        currentUser = User(),
        onClick = {
            index, chat ->
        }
    )
}
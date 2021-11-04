package com.trian.component.appbar

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Mail16

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 24/10/2021
 */
@Composable
fun ChatEntry(
    modifier: Modifier = Modifier,
    onSend:(message:String)->Unit
) {
    var message by remember {
        mutableStateOf("")
    }
   Row(
       modifier=modifier.padding(
           start = 6.dp,end = 6.dp,
           top = 2.dp,bottom = 2.dp
       ),
        horizontalArrangement = Arrangement.SpaceBetween,
       verticalAlignment = Alignment.CenterVertically
   ) {
       TextField(value = message, onValueChange = {
           message = it
       })
       IconToggleButton(checked = false, onCheckedChange = {
           onSend(message)
       }) {
           Icon(
               modifier=modifier.padding(horizontal = 6.dp),
               imageVector = Octicons.Mail16,
               contentDescription = ""
           )
       }
   }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
)
@Composable
fun PreviewChatEntryLight(){
    ChatEntry(

    ) {

    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewChatEntryDark(){
    ChatEntry(

    ) {

    }
}
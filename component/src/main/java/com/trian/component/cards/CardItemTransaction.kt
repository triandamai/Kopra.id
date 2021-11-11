package com.trian.component.cards

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.common.utils.utils.formatReadableDate
import com.trian.component.R
import com.trian.domain.models.ChatItem
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
    transaction:Transaction,
    onChatSender:(index:Int,transaction:Transaction)->Unit,
    onClick:(index:Int, transaction:Transaction)-> Unit
){
   Column(modifier = modifier
       .clip(RoundedCornerShape(12.dp))
       .clickable {onClick(index,transaction) }
       .padding(vertical = 8.dp, horizontal = 16.dp)
       .background(Color.White)) {
       Row(
           modifier= Modifier
               .clip(RoundedCornerShape(12.dp))
               .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
       ) {

           Image(
               modifier = modifier
                   .size(60.dp)
                   .clip(RoundedCornerShape(12.dp)),
               contentScale= ContentScale.FillWidth,
               painter = painterResource(id = R.drawable.dummy_profile),
               contentDescription = "Sender Picture"
           )
           Column(
               modifier=modifier.padding(horizontal = 8.dp),
               horizontalAlignment = Alignment.Start,
               verticalArrangement = Arrangement.Top
           ) {
               Row(
                   modifier = modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween,
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   Text(transaction.desc)
                   Text(transaction.createdAt.formatReadableDate())
               }
               Spacer(modifier =modifier.height(10.dp))
               Text(
                   "Rp ${transaction.totalPrice}",
                   style = TextStyle(
                       fontSize = 18.sp,

                   )
               )

           }
       }
       Spacer(modifier =modifier.height(8.dp))
       Row {
           TextButton(onClick = {
               onChatSender(index,transaction)
           }) {
               Text(text = "Hubungi penjual")
           }
       }
       Spacer(modifier =modifier.height(8.dp))
       Divider()
   }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewCardItemChatTransactionLight(){
        CardItemTransaction(transaction = Transaction(), onChatSender = {
                                                                        index, transaction ->
        },onClick = {
            index, chat ->
        })
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCardItemChatTransactionDark(){
    CardItemTransaction(transaction = Transaction(),
        onChatSender={
                     index, transaction ->
        },
        onClick = {
            index, chat ->
    })
}
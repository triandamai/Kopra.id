package com.trian.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.common.utils.network.NetworkStatus
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import compose.icons.Octicons
import compose.icons.octicons.Circle24
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BottomSheetPrivacyPolicy(m:Modifier = Modifier, textStyle: TextStyle = TextStyle(),nav:NavHostController){
    var scaledTextStyle by remember { mutableStateOf(textStyle) }
    var readyToDraw by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
        ),
        modifier = m.fillMaxWidth()
    ) {
       Column(modifier = m.padding(10.dp)) {
           Text(
               text = "Syarat Penggunaan & Kebijakan Privasi",
               modifier = m
                   .drawWithContent {
                       if(readyToDraw){ drawContent()
                       }
                   },
               style = scaledTextStyle.copy(
                   fontWeight = FontWeight.Bold,
                   fontSize = 16.sp
               ),
               softWrap = true,
               onTextLayout = {
                       textLayoutResult ->
                   if(textLayoutResult.didOverflowWidth){
                       scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                   }else{
                       readyToDraw = true
                   }
               }
           )
           Divider(modifier = m
               .fillMaxWidth()
               .padding(top = 10.dp, bottom = 10.dp))
           Text(
               text = "Cexup telah memperbarui syarat penggunaan dan kebijakan privasi",
               modifier = m
                   .drawWithContent {
                       if(readyToDraw){ drawContent()
                       }
                   },
               style = scaledTextStyle.copy(
                   fontWeight = FontWeight.Bold,
                   fontSize = 24.sp
               ),
               softWrap = true,
               onTextLayout = {
                       textLayoutResult ->
                   if(textLayoutResult.didOverflowWidth){
                       scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                   }else{
                       readyToDraw = true
                   }
               }
           )
           Spacer(modifier = m.height(20.dp))
           Text(
               text = "Syarat penggunaan dan kebijakan Privasi merupakan ketentuan yang harus dibaca, dipahami, dan disetejui oleh Pengguna sebelum mengakses atau menggunakan aplikasi Cexup. Lihat Syarat dan Penggunaan dan Kebijakan Privasi di sini :",
               modifier = m
                   .drawWithContent {
                       if(readyToDraw){ drawContent()
                       }
                   },
               style = scaledTextStyle,
               softWrap = true,
               onTextLayout = {
                       textLayoutResult ->
                   if(textLayoutResult.didOverflowWidth){
                       scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                   }else{
                       readyToDraw = true
                   }
               }
           )
           Spacer(modifier = m.height(20.dp))
           Column {
               Row(verticalAlignment = Alignment.CenterVertically){
                   Icon(
                       Octicons.Circle24,
                       contentDescription = "",
                       modifier = m.width(10.dp)
                   )
                   Spacer(modifier = m.width(8.dp))
                   Text(
                       text = "Syarat Penggunaan",
                       modifier = m
                           .drawWithContent {
                               if(readyToDraw){ drawContent()
                               }
                           },
                       style = scaledTextStyle.copy(color = BluePrimary),
                       softWrap = true,
                       onTextLayout = {
                               textLayoutResult ->
                           if(textLayoutResult.didOverflowWidth){
                               scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                           }else{
                               readyToDraw = true
                           }
                       }
                   )
               }
               Spacer(modifier = m.height(10.dp))
               Row(verticalAlignment = Alignment.CenterVertically){
                   Icon(
                       Octicons.Circle24,
                       contentDescription = "",
                       modifier = m.width(10.dp)
                   )
                   Spacer(modifier = m.width(8.dp))
                   Text(
                       text = "Kebijakan Privasi",
                       modifier = m
                           .drawWithContent {
                               if(readyToDraw){ drawContent()
                               }
                           },
                       style = scaledTextStyle.copy(color = BluePrimary),
                       softWrap = true,
                       onTextLayout = {
                               textLayoutResult ->
                           if(textLayoutResult.didOverflowWidth){
                               scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                           }else{
                               readyToDraw = true
                           }
                       }
                   )
               }
               Spacer(modifier = m.height(10.dp))
               Text(
                   text = "Dengan menyatakan Setuju. Anda menerima segala isi Syarat Penggunaan dan Kebijakan Privasi yang baru.",
                   modifier = m
                       .drawWithContent {
                           if(readyToDraw){ drawContent()
                           }
                       },
                   style = scaledTextStyle,
                   softWrap = true,
                   onTextLayout = {
                           textLayoutResult ->
                       if(textLayoutResult.didOverflowWidth){
                           scaledTextStyle = scaledTextStyle.copy(fontSize =scaledTextStyle.fontSize*0.9)
                       }else{
                           readyToDraw = true
                       }
                   }
               )
               Spacer(modifier = m.height(15.dp))
               Button(
                   onClick ={nav.navigate(Routes.LOGIN)},
                   modifier = m.fillMaxWidth(),
                   colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                   shape = RoundedCornerShape(8.dp)) {
                       Text(
                           text = "Saya Setuju",
                           style = MaterialTheme.typography.h1.copy(
                               fontWeight = FontWeight.Normal,
                               fontSize = 16.sp,
                               letterSpacing = 1.sp,
                               color = Color.White
                           ),
                           modifier = m.padding(10.dp),
                       )
                   }
               }
           }
       }
}
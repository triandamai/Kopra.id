package com.trian.component.bottomsheet

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.PermissionUtils
import com.trian.component.ui.theme.BluePrimary
import compose.icons.Octicons
import compose.icons.octicons.Circle24

@Composable
fun BottomSheetPrivacyPolicy(
    modifier:Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    permissionUtils:PermissionUtils,
    nav:NavHostController
){
    var scaledTextStyle by remember { mutableStateOf(textStyle) }
    var readyToDraw by remember { mutableStateOf(false) }
    var snackbarState by remember { mutableStateOf(false)}

    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()){
        val check = it.values.contains(false)
        if(!check){
            nav.navigate(Routes.LOGIN){
                launchSingleTop=true
                popUpTo(Routes.ONBOARD){
                    inclusive=true
                }
            }
        }else{
            snackbarState = true
        }
    }
    if(snackbarState){
        Snackbar {
            Text("Permission not Granted")
        }
    }
    Card(
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
        ),
        modifier = modifier.fillMaxWidth()
    ) {
       Column(modifier = modifier.padding(10.dp)) {
           Text(
               text = "Syarat & Ketentuan Penggunaan Cexup",
               modifier = modifier
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
           Divider(modifier = modifier
               .fillMaxWidth()
               .padding(top = 10.dp, bottom = 10.dp))
           Text(
               text = "Cexup telah memperbarui Syarat & Ketentuan Penggunaan",
               modifier = modifier
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
           Spacer(modifier = modifier.height(20.dp))
           Text(
               text = "Syarat & Ketentuan Penggunaan merupakan ketentuan yang harus dibaca, dipahami, dan disetejui oleh Pengguna sebelum mengakses atau menggunakan aplikasi Cexup. Lihat Syarat & Ketentuan Penggunaan Cexup di sini :",
               modifier = modifier
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
           Spacer(modifier = modifier.height(20.dp))
           Row(verticalAlignment = Alignment.CenterVertically){
               Icon(
                   Octicons.Circle24,
                   contentDescription = "",
                   modifier = modifier.width(10.dp)
               )
               Spacer(modifier = modifier.width(8.dp))
               Text(
                   text = "Syarat dan Ketentuan Penggunaan",
                   modifier = modifier
                       .drawWithContent {
                           if (readyToDraw) {
                               drawContent()
                           }
                       }
                       .clickable { nav.navigate(Routes.PRIVACY_POLICY) },
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
               Spacer(modifier = modifier.height(20.dp))
               Text(
                   text = "Dengan menyatakan Setuju. Anda menerima segala isi Syarat & Ketentuan Penggunaan Cexup yang baru.",
                   modifier = modifier
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
               Spacer(modifier = modifier.height(15.dp))
               Button(
                   onClick ={

                       when(
                           permissionUtils.hasPermission()
                       ){
                           true->{
                                  nav.navigate(Routes.LOGIN)
                           }
                           false->{

                               permissionLauncher.launch(
                                   permissionUtils.listPermission()
                               )
                           }
                       }


                   },
                   modifier = modifier.fillMaxWidth(),
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
                           modifier = modifier.padding(10.dp),
                       )
                   }
               }
           }
       }
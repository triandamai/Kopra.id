package com.trian.kopra.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.component.R
import com.trian.component.ui.theme.GreenPrimary
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

@Composable
fun PageAbout(
    modifier: Modifier=Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navHostController: NavHostController,
    onMessageClicked:(phone:String)->Unit
) {
    val phone = "+6281237529187"
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                                 IconToggleButton(checked = false, onCheckedChange = {
                                     navHostController.popBackStack()
                                 }) {
                                     Icon(
                                         imageVector = Octicons.ArrowLeft24,
                                         contentDescription = "",
                                         tint=Color.White,
                                     )
                                 }
                },
                backgroundColor=GreenPrimary,
                title = {
                    Text(
                        text = "Tentang Aplikasi",
                        color = Color.White
                    )
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_kopra), contentDescription = "",
                modifier = modifier.height(80.dp)
                    .width(80.dp)
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                modifier=modifier.fillMaxWidth(),
                text = "Adalah Platform Inovatif untuk Monitoring Waktu Panen Kelapa dan Pendistribusian Kopra",
                textAlign = TextAlign.Center,
                style= TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                modifier=modifier.fillMaxWidth(),
                text = "Kontak Kami",
                textAlign = TextAlign.Center,
                style= TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp
                )
            )

            Spacer(modifier = modifier.height(16.dp))
            Text(
                modifier= modifier
                    .clickable {
                        onMessageClicked(phone)
                    }
                    .fillMaxWidth(),
                text = phone,
                textAlign = TextAlign.Center,
                style= TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Blue
                )
            )
        }
    }
    
}
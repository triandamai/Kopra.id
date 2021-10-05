package com.trian.module.ui.pages.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.FontDeviceConnected
import com.trian.data.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope

/**
 * Confirmation email
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 05/10/2021
 */

@Composable
fun PageRegisterConfirmation(
    modifier:Modifier=Modifier,
    viewModel: MainViewModel,
    scope: CoroutineScope,
    nav:NavHostController
){
  Scaffold {
      Surface(
          modifier = modifier
              .fillMaxSize()
              .padding(16.dp),
          shape = RoundedCornerShape(5.dp),
          color = Color.White
      ) {
          Column(
              modifier = modifier
                  .fillMaxWidth()
                  .padding(vertical = 16.dp)
                  .background(MaterialTheme.colors.background),
              verticalArrangement = Arrangement.Center

          ) {
              Image(
                  painter = painterResource(id = R.drawable.ic_email),
                  contentDescription = "",
                  modifier = modifier
                      .fillMaxWidth()
                      .padding(start = 16.dp, end = 16.dp)
                      .size(130.dp)
              )
              Text(
                  text = "Check Your Mail",
                  fontWeight = FontWeight.Bold,
                  fontSize = 24.sp,
                  modifier = modifier
                      .fillMaxWidth()
                      .padding(
                          start = 16.dp,
                          end = 16.dp,
                          top = 16.dp,
                      ),
                  textAlign = TextAlign.Center
              )
              Text(
                  text = "Please check your email to confirm your registration.",
                  modifier = modifier
                      .padding(
                          start = 16.dp,
                          end = 16.dp,
                          top = 16.dp,
                          bottom = 16.dp
                      )
                      .fillMaxWidth(),
                  textAlign = TextAlign.Center,
                  color = FontDeviceConnected,
                  fontSize = 12.sp
              )
              Row(
                  modifier = modifier
                      .padding( horizontal = 16.dp)
                      .fillMaxWidth(),
                  verticalAlignment = Alignment.Bottom
              ) {
                  Button(
                      onClick ={
                               nav.popBackStack()
                      },
                      modifier = modifier
                          .fillMaxWidth(),
                      colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                      shape = RoundedCornerShape(15.dp)
                  ) {
                      Text(
                          text = "OK,Go to Login",
                          style = MaterialTheme.typography.h1.copy(
                              fontWeight = FontWeight.Medium,
                              fontSize = 14.sp,
                              letterSpacing = 1.sp,
                              color = Color.White
                          ),
                          modifier = modifier.padding(10.dp))
                  }
              }
          }
      }
  }
}
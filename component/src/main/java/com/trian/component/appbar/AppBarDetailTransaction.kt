package com.trian.component.appbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

/**
 * Base Application
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 24/10/2021
 */
@Composable
fun AppBarDetailTransaction(onBackPressed:()->Unit) {
    TopAppBar(
      navigationIcon = {
        Icon(imageVector = Octicons.ArrowLeft24, contentDescription = "Back")
      },
      title = {
          Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
              Text("Sewa Kendaraan")
              Text("Transaksi pada 24 Oktober 2021")
          }
      }
    )
}
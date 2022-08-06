package com.trian.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.android.gms.maps.model.LatLng
import com.trian.component.cards.CardPickGoogleMap

@Composable
fun DialogPickMap(
    modifier: Modifier = Modifier,
    show:Boolean,
    location: LatLng,
    onCancel: ()-> Unit,
    onLocation:(LatLng)-> Unit
) {

    var resultLocation by remember {
        mutableStateOf(LatLng(0.0,0.0))
    }
    if(show){
        Dialog(onDismissRequest = { onCancel() }) {
           Column(
               modifier=modifier.background(
                   MaterialTheme.colors.surface
               )
           ) {
               CardPickGoogleMap(
                   location = location,
                   onLocation ={
                       resultLocation = it
                   }
               )
               Spacer(
                   modifier = modifier.height(16.dp)
               )
               Row {
                   TextButton(onClick = { onCancel() }) {
                       Text(text = "Batal")
                   }
                   TextButton(onClick = {
                       onLocation(resultLocation)
                   }) {
                       Text(text = "Simpan")
                   }
               }
           }
        }
    }
}
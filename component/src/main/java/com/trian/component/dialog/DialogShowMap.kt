package com.trian.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.google.android.gms.maps.model.LatLng
import com.trian.component.cards.CardShowGoogleMap

@Composable
fun DialogShowMap(
    modifier: Modifier = Modifier,
    show:Boolean=false,
    latitude:Double=-6.206623,
    longitude:Double=106.7350596,
    name:String="",
    onClose:()->Unit
) {
    if(show){
        Dialog(onDismissRequest = { onClose() }) {
            Column {
                CardShowGoogleMap(
                    modifier = modifier
                        .fillMaxHeight(0.9f),
                    name = name,
                    location = LatLng(latitude,longitude)
                )
            }
        }
    }
}
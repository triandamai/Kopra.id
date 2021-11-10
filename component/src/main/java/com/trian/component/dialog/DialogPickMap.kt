package com.trian.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.google.android.libraries.maps.model.LatLng
import com.trian.component.cards.CardPickGoogleMap

@Composable
fun DialogPickMap(
    modifier: Modifier = Modifier,
    show:Boolean,
    location: LatLng,
    onCancel: ()-> Unit,
    onLocation:(LatLng)-> Unit
) {

    if(show){
        Dialog(onDismissRequest = { onCancel() }) {
            CardPickGoogleMap(location = location, onLocation = onLocation)
        }
    }
}
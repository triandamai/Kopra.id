package com.trian.component.cards

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.libraries.maps.model.PolylineOptions
import com.google.maps.android.ktx.awaitMap
import com.trian.component.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Page Dashboard List Chat
 * Author Trian damai
 * Created by Trian Damai
 * 10/11/2021
 */
@Composable
fun CardShowGoogleMap(
    modifier: Modifier=Modifier,
    location:LatLng,
    name:String
) {
    val mapView = rememberMapViewWithLifeCycle()
    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.White)) {
        AndroidView({mapView}){
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true
                map.moveCamera(
                    CameraUpdateFactory
                        .newLatLngZoom(location, 18f)
                )
                val markerOptionsDestination = MarkerOptions()
                    .title(name)
                    .position(location)
                map.addMarker(markerOptionsDestination)


            }
        }
    }
}




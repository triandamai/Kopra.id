package com.trian.component.cards

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
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
@SuppressLint("MissingPermission")
@Composable
fun CardPickGoogleMap(
    modifier: Modifier=Modifier,
    location:LatLng,
    onLocation:(LatLng)->Unit
) {
    val mapView = rememberMapViewWithLifeCycle()
    var currentLocation by remember {
        mutableStateOf(location)
    }

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
                    .title("Lokasi Toko Saya")
                    .position(location)
                map.addMarker(markerOptionsDestination)
                map.isMyLocationEnabled =true
                map.setOnMapClickListener {
                    clickLocation->
                    map.animateCamera(
                        CameraUpdateFactory
                            .newLatLngZoom(clickLocation, 10f)
                    )

                    map.clear()
                    val pickLocation = MarkerOptions()
                        .title("Lokasi Toko Saya")
                        .position(clickLocation)
                    map.addMarker(pickLocation)

                    currentLocation = clickLocation
                }

            }
        }

    }
}




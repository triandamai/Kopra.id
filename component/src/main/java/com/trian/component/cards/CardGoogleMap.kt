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
import java.lang.IllegalStateException

/**
 * Page Dashboard List Chat
 * Author Trian damai
 * Created by Trian Damai
 * 10/11/2021
 */
@Composable
fun CardGoogleMap(modifier: Modifier=Modifier) {
    val mapView = rememberMapViewWithLifeCycle()
    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.White)) {
        AndroidView({mapView}){
            CoroutineScope(Dispatchers.Main).launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true
                val pickUp = LatLng(28.7041, 77.1025) //Delhi
                val destination = LatLng(12.9716, 77.5946) //Bangalore
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 6f))
                val markerOptions =  MarkerOptions()
                    .title("Delhi")
                    .position(pickUp)
                map.addMarker(markerOptions)
                val markerOptionsDestination = MarkerOptions()
                    .title("Bangalore")
                    .position(destination)
                map.addMarker(markerOptionsDestination)

                map.addPolyline(
                    PolylineOptions().add(
                        pickUp,
                        LatLng(22.2587, 71.1924), //Root of Gujarat
                        LatLng(19.7515, 75.7139), //Root of Maharashtra
                        destination
                    )
                ).color = R.color.purple_200 //Polyline color
            }
        }
    }
}

@Composable
fun rememberMapViewWithLifeCycle():MapView{
    val ctx = LocalContext.current
    val mapView = remember {
        MapView(ctx).apply {
            id = com.google.maps.android.ktx.R.id.map_frame
        }
    }
    val lifeCycleObserver = rememberMapLifeCycleObserver(mapView = mapView)
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(key1 = lifeCycle, effect = {
        lifeCycle.addObserver(lifeCycleObserver)
        onDispose {
            lifeCycle.removeObserver(lifeCycleObserver)
        }
    })
    return mapView
}

@Composable
fun rememberMapLifeCycleObserver(mapView:MapView):LifecycleEventObserver = remember(mapView) {
    LifecycleEventObserver { _, event ->
        when(event){
            Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
            Lifecycle.Event.ON_START -> mapView.onStart()
            Lifecycle.Event.ON_RESUME -> mapView.onResume()
            Lifecycle.Event.ON_PAUSE -> mapView.onPause()
            Lifecycle.Event.ON_STOP -> mapView.onStop()
            Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
            else -> throw IllegalStateException()
        }
    }

}
package com.trian.component.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location

import androidx.lifecycle.LiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.net.URI.create


data class LocationModel(
    var latitude:Double,
    var longitude:Double
)
class LocationLiveData(context: Context) : LiveData<LocationModel>() {

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    companion object {
        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location?->
                location?.also {
                    setLocationData(it)
                }
            }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {

            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }

    }

    private fun setLocationData(location: Location) {
        value = LocationModel(
            longitude = location.longitude,
            latitude = location.latitude
        )
    }
}
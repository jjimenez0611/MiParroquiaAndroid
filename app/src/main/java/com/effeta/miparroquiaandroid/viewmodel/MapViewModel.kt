package com.effeta.miparroquiaandroid.viewmodel

import android.Manifest
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.pm.PackageManager
import android.location.Location
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.effeta.miparroquiaandroid.common.FASTEST_INTERVAL
import com.effeta.miparroquiaandroid.common.UPDATE_INTERVAL
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

/**
* Created by jjimenez on 3/7/18.
*/
class MapViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var mLocationRequest: LocationRequest = LocationRequest()

    // Trigger new location updates at interval
    fun initLocationUpdates(): LocationSettingsRequest? {

        // Create the location request to start receiving updates
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = UPDATE_INTERVAL
        mLocationRequest.fastestInterval = FASTEST_INTERVAL
        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()

        builder.addLocationRequest(mLocationRequest)

        return builder.build()

    }

    fun getLocationRequest(): LocationRequest {
        return mLocationRequest
    }

    /**
     * This fun could be used to update the map if the location change
     */
    fun onLocationChanged(location: Location) {
        // New location has now been determined
        val msg = "Updated Location: " +
                java.lang.Double.toString(location.latitude) + "," +
                java.lang.Double.toString(location.longitude)
        val latLng = LatLng(location.latitude, location.longitude)
        Log.d("new Location", msg)
    }

    var hasPermission: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * fun to check the locations permissions
     */
    fun checkLocationPermission(): Boolean {
        return if (ActivityCompat.checkSelfPermission(getApplication(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            hasPermission.postValue(false)
            false
        }
    }

}
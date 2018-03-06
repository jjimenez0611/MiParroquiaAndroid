package com.effeta.miparroquiaandroid.views.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseFragment
import com.effeta.miparroquiaandroid.common.FASTEST_INTERVAL
import com.effeta.miparroquiaandroid.common.REQUEST_FINE_LOCATION
import com.effeta.miparroquiaandroid.common.UPDATE_INTERVAL
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.viewmodel.ChurchMapViewModel
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_announcements.*
import javax.inject.Inject


class ChurchMapFragment : BaseFragment(), OnMapReadyCallback {

    override val mLayout: Int = R.layout.fragment_church_map

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mChurchViewModel: ChurchMapViewModel

    private lateinit var mMap: GoogleMap

    private var mLocationRequest: LocationRequest? = null

    private lateinit var churches: List<Church>

    override fun initializeViewModels() {
        mChurchViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ChurchMapViewModel::class.java)
    }

    override fun initializeUI() {
    }

    /**
     * Here we get the map Fragment is necessary use childFragmentManager because is in a fragment
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mChurchViewModel.getChurches()

    }

    override fun observeLiveData(isNewActivity: Boolean) {

        mChurchViewModel.isError.observe(this, Observer {
            Toast.makeText(this@ChurchMapFragment.context, R.string.error_to_load_map_church, Toast.LENGTH_SHORT).show()
        })
        mChurchViewModel.getChurches().observe(this, Observer {
            progress.visibility = View.GONE
            content.visibility = View.VISIBLE

            val mapFragment = childFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
            churches = it!!
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        // Add a marker in San Jose Costa Rica and move the camera

        //Get the point
        if (!churches.isEmpty()) {
            showMapPoints(churches)
        } else {
            val mSJCR = LatLng(9.934739, -84.087502)
            mMap.addMarker(MarkerOptions().position(mSJCR))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mSJCR, 15F))
        }
        if (checkLocationPermission()) {
            mMap.isMyLocationEnabled = true
            startLocationUpdates()
        }
    }

    /**
     * This method is used to show the church on the map
     */
    private fun showMapPoints(list: List<Church>?) {
        mMap.clear()
        list?.let {
            var pointToAdd: LatLng? = null
            for (item in list) {
                pointToAdd = LatLng(item.mUbication!!.latitude, item.mUbication!!.longitude)
                mMap.addMarker(MarkerOptions().position(pointToAdd).title(String.format(getString(R.string.map_label_church), item.mName)))
            }
            pointToAdd?.let { mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pointToAdd, 17F)) }
        }
    }

    /**
     * fun to check the locations permissions
     */
    private fun checkLocationPermission(): Boolean {
        return if (ActivityCompat.checkSelfPermission(activity,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            requestLocationPermissions()
            false
        }
    }

    /**
     * fun to retrieve the permissions
     */
    private fun requestLocationPermissions() {
        requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FINE_LOCATION)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_FINE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    startLocationUpdates()
                    mMap.isMyLocationEnabled = true
                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

    // Trigger new location updates at interval
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {

        // Create the location request to start receiving updates
        mLocationRequest = LocationRequest()
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest!!.interval = UPDATE_INTERVAL
        mLocationRequest!!.fastestInterval = FASTEST_INTERVAL
        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        val settingsClient = LocationServices.getSettingsClient(activity)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        getFusedLocationProviderClient(activity).requestLocationUpdates(mLocationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                // do work here
                onLocationChanged(locationResult!!.lastLocation)
            }
        }, Looper.myLooper())
    }

    /**
     * This fun could be used to update the map if the location change
     */
    fun onLocationChanged(location: Location) {
        // New location has now been determined
        val msg = "Updated Location: " +
                java.lang.Double.toString(location.latitude) + "," +
                java.lang.Double.toString(location.longitude)
        // Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        // You can now create a LatLng Object for use with maps
        val latLng = LatLng(location.latitude, location.longitude)
    }

}

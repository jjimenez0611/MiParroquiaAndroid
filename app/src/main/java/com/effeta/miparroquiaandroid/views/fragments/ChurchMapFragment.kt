package com.effeta.miparroquiaandroid.views.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseFragment
import com.effeta.miparroquiaandroid.common.EXTRA_CHURCH
import com.effeta.miparroquiaandroid.common.REQUEST_FINE_LOCATION
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.utils.MapUtils
import com.effeta.miparroquiaandroid.viewmodel.ChurchMapViewModel
import com.effeta.miparroquiaandroid.viewmodel.MapViewModel
import com.effeta.miparroquiaandroid.views.activities.DetailMapActivity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_announcements.*
import java.io.Serializable
import javax.inject.Inject

class ChurchMapFragment : BaseFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override val mLayout: Int = R.layout.fragment_church_map

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mChurchViewModel: ChurchMapViewModel

    private lateinit var mMapViewModel: MapViewModel

    private lateinit var mMap: GoogleMap

    private lateinit var mLocationCallback: LocationCallback

    override fun initializeViewModels() {
        mChurchViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ChurchMapViewModel::class.java)

        mMapViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MapViewModel::class.java)
    }

    override fun initializeUI() {
    }

    override fun onResume() {
        super.onResume()
        if (mMapViewModel.checkLocationPermission()) {
            startLocationUpdates()
        }
    }

    /**
     * Here we get the map Fragment is necessary use childFragmentManager because is in a fragment
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLocationCallback()
        initMapFragment()
    }

    override fun observeLiveData(isNewActivity: Boolean) {
        mMapViewModel.hasPermission.observe(this, Observer {
            if (!it!!) {
                requestLocationPermissions()
            }
        })
    }

    private fun initMapFragment() {
        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Fun to know when the map aReady to use
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        // Add a marker in San Jose Costa Rica and move the camera

        if (mMapViewModel.checkLocationPermission()) {
            mMap.isMyLocationEnabled = true
        }

        //Set Churches Observes when the map are ready
        mChurchViewModel.isError.observe(this, Observer {
            Toast.makeText(this@ChurchMapFragment.context, R.string.error_to_load_map_church, Toast.LENGTH_SHORT).show()
        })

        mChurchViewModel.getChurches().observe(this, Observer {
            progress.visibility = View.GONE
            content.visibility = View.VISIBLE
            showMapPoints(it!!)
        })

        mMap.setOnMarkerClickListener(this)
    }

    /**
     * This method is used to show the church on the map
     */
    private fun showMapPoints(list: List<Church>?) {
        mMap.clear()
        list?.let {
            //Get the point
            if (!list.isEmpty()) {
                var pointToAdd: LatLng? = null
                for (item in list) {
                    pointToAdd = LatLng(item.mLatitude!!, item.mLongitude!!)
                    mMap.addMarker(MarkerOptions()
                            .icon(MapUtils.getMarkerIconFromDrawable(ContextCompat.getDrawable(context, R.drawable.marker_map_church)))
                            .position(pointToAdd)
                            .title(String.format(getString(R.string.map_label_church), item.mName))).tag = item
                }
                pointToAdd?.let { mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pointToAdd, 12F)) }
            }
        }
    }


    /**
     * trigger when a marker was clicked
     */
    override fun onMarkerClick(p0: Marker?): Boolean {

        val church = p0?.tag as Church
        val intent = Intent(context, DetailMapActivity::class.java)
        intent.putExtra(EXTRA_CHURCH, church as Serializable)
        startActivity(intent)
        return false
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
                    // permission was granted.
                    startLocationUpdates()
                    mMap.isMyLocationEnabled = true
                }
                return
            }
        }// other 'case' lines to check for other
    }

    /**
     *  Trigger new location updates at interval
     */
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        val settingsClient = LocationServices.getSettingsClient(activity)
        settingsClient.checkLocationSettings(mMapViewModel.initLocationUpdates())

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        getFusedLocationProviderClient(activity).requestLocationUpdates(mMapViewModel.getLocationRequest(), mLocationCallback, null)
    }

    /**
     * Fun to manage the location callback, this fun is called on the onResume method to start the location updates
     */
    private fun initLocationCallback() {
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                /* for (location in locationResult.locations) {
                     // Update UI with location data
                     // ...
                 }*/
            }
        }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    /**
     * fun to stop the location updates is called on the onPause
     */
    private fun stopLocationUpdates() {
        getFusedLocationProviderClient(activity).removeLocationUpdates(mLocationCallback)
    }


}

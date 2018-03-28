package com.effeta.miparroquiaandroid.views.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseFragment
import com.effeta.miparroquiaandroid.common.EXTRA_CHURCH
import com.effeta.miparroquiaandroid.common.REQUEST_FINE_LOCATION
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.utils.MapUtils
import com.effeta.miparroquiaandroid.viewmodel.ChurchListViewModel
import com.effeta.miparroquiaandroid.viewmodel.MapViewModel
import com.effeta.miparroquiaandroid.views.activities.ChurchDetailActivity
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
import kotlinx.android.synthetic.main.marker_info_layout.view.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class ParishMapFragment : BaseFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    override val mLayout: Int = R.layout.fragment_parish_map

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mChurchViewModel: ChurchListViewModel

    private lateinit var mMapViewModel: MapViewModel

    private lateinit var mMap: GoogleMap

    private lateinit var mLocationCallback: LocationCallback

    override fun initializeViewModels() {
        mChurchViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ChurchListViewModel::class.java)

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
        getViewLifecycleOwner()?.let {
            mMapViewModel.hasPermission.observe(it, Observer { hasPermission ->
                if (!hasPermission!!) {
                    requestLocationPermissions()
                }
            })
        }
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
            Toast.makeText(this@ParishMapFragment.context, R.string.error_to_load_map_church, Toast.LENGTH_SHORT).show()
        })
        mChurchViewModel.getChurches().observe(this, Observer {
            progress.visibility = View.GONE
            content.visibility = View.VISIBLE
            showMapPoints(it!!)

        })
        mMap.setInfoWindowAdapter(CustomMarkerInformation())
        mMap.setOnMarkerClickListener(this)
        mMap.setOnInfoWindowClickListener(this)

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
                            .position(pointToAdd)).tag = item
                }
                pointToAdd?.let { mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pointToAdd, 12F)) }
            }
        }
    }

    /**
     * fun used to go the detail activity
     */
    override fun onInfoWindowClick(p0: Marker?) {
        val church = p0?.tag as Church
        startActivity(context.intentFor<ChurchDetailActivity>(Pair(EXTRA_CHURCH, church.mKey)))
    }

    /**
     * Fun used to show the custom info dialog
     */
    override fun onMarkerClick(p0: Marker?): Boolean {
        p0!!.showInfoWindow()
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
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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

    internal inner class CustomMarkerInformation : GoogleMap.InfoWindowAdapter {

        private val window: View = layoutInflater.inflate(R.layout.marker_info_layout, null)

        override fun getInfoContents(p0: Marker?): View {
            val church = p0?.tag as Church
            window.church_name.text = String.format(getString(R.string.map_label_church), church.mName)
            window.message.text = getString(R.string.marker_info_message)
            return window
        }

        override fun getInfoWindow(p0: Marker?): View? {
            return null
        }

    }

}

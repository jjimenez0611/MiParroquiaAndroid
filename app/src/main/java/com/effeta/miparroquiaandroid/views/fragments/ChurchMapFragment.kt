package com.effeta.miparroquiaandroid.views.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseFragment
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.viewmodel.ChurchMapViewModel
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
        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun observeLiveData(isNewActivity: Boolean) {

        mChurchViewModel.isError.observe(this, Observer {
            Toast.makeText(this@ChurchMapFragment.context, R.string.error_to_load_map_church, Toast.LENGTH_SHORT).show()
        })
        mChurchViewModel.mChurchList.observe(this, Observer {
            progress.visibility = View.GONE
            content.visibility = View.VISIBLE
            showMapPoints(it)
        })
    }

    /**
     * We can't use this method, because we need to wait to onMapReady to fetch Data, we use the getChurch.
     */
    override fun fetchData() {
    }

    /**
     * This method is used to show the church on the map
     */
    private fun showMapPoints(list: List<Church>?) {
        mMap.clear()
        var pointToAdd = LatLng(9.934739, -84.087502)
        for (item in list!!) {
            pointToAdd = LatLng(item.mUbication!!.latitude, item.mUbication!!.longitude)
            mMap.addMarker(MarkerOptions().position(pointToAdd).title(String.format(getString(R.string.title_map_point), item.mName)))
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pointToAdd, 13F))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        // Add a marker in San Jose Costa Rica and move the camera
        val SJCR = LatLng(9.934739, -84.087502)
        mMap.addMarker(MarkerOptions().position(SJCR))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(SJCR, 15F))

        //Get the point
        mChurchViewModel.getChurches()
    }

}

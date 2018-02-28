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

    lateinit var mChurchViewModel: ChurchMapViewModel

    private lateinit var mMap: GoogleMap

    override fun initializeViewModels() {
        mChurchViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ChurchMapViewModel::class.java)
    }

    override fun initializeUI() {
    }

    override fun observeLiveData(isNewActivity: Boolean) {

        mChurchViewModel.isError.observe(this, Observer {
            Toast.makeText(this@ChurchMapFragment.context, "Error al cargar los puntos.", Toast.LENGTH_SHORT).show()
        })
        mChurchViewModel.mChurchList.observe(this, Observer {
            progress.visibility = View.GONE
            content.visibility = View.VISIBLE
            showMapPoints(it)
        })
    }

    override fun fetchData() {

    }


    private fun showMapPoints(list: List<Church>?) {
        mMap.clear()
        var pointToAdd = LatLng(9.934739, -84.087502)
        for(item in list!!){
            pointToAdd = LatLng(item.mUbication!!.latitude,item.mUbication!!.longitude)
            mMap.addMarker(MarkerOptions().position(pointToAdd).title(String.format("%s %s","Iglesia de" , item.mName)))
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pointToAdd, 13F))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        // Add a marker in Sydney and move the camera
        val SJCR = LatLng(9.934739, -84.087502)
        mMap.addMarker(MarkerOptions().position(SJCR))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(SJCR, 15F))

        //Get the point
        mChurchViewModel.getChurchs()
    }

}

package com.effeta.miparroquiaandroid.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.content.ContextCompat
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseActivity
import com.effeta.miparroquiaandroid.common.EXTRA_CHURCH
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.utils.MapUtils
import com.effeta.miparroquiaandroid.viewmodel.DetailMapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_map.*
import javax.inject.Inject

/**
 * Created by jjimenez on 3/12/18.
 */

class DetailMapActivity : BaseActivity(), OnMapReadyCallback {

    override val mLayout: Int = R.layout.activity_detail_map

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mMap: GoogleMap


    private lateinit var mDetailMapViewModel: DetailMapViewModel

    override fun initializeViewModels() {
        mDetailMapViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailMapViewModel::class.java)
    }

    override fun initializeUI() {
        setSupportActionBar(toolbar_detail)
        toolbar_detail.title = getString(R.string.detail_map_activity_title)
        toolbar_detail.setNavigationOnClickListener { finish() }

        initMapFragment()
    }

    private fun initMapFragment() {
        val mapFragment = map as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun observeLiveData() {
        mDetailMapViewModel.getChurch().observe(this, Observer {
            showChurchInformation(it!!)
        })
    }

    private fun showChurchInformation(church: Church) {
        church_name.text = String.format(getString(R.string.map_label_church), church.mName)
        addPointToMap(church.mLatitude!!, church.mLongitude!!, church.mName)
    }

    private fun addPointToMap(latitude:Double, longitude:Double, name:String){
        val pointToAdd = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions()
                .icon(MapUtils.getMarkerIconFromDrawable(ContextCompat.getDrawable(this, R.drawable.marker_map_church)))
                .position(pointToAdd)
                .title(String.format(getString(R.string.map_label_church), name)))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pointToAdd, 17F))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        if (intent.hasExtra(EXTRA_CHURCH)) {
            mDetailMapViewModel.setChurchInformation(intent.extras.get(EXTRA_CHURCH) as Church)
        }

    }
}
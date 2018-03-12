package com.effeta.miparroquiaandroid.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.BaseActivity
import com.effeta.miparroquiaandroid.common.EXTRA_CHURCH
import com.effeta.miparroquiaandroid.models.Church
import com.effeta.miparroquiaandroid.viewmodel.DetailMapViewModel
import kotlinx.android.synthetic.main.activity_detail_map.*
import javax.inject.Inject

/**
 * Created by jjimenez on 3/12/18.
 */

class DetailMapActivity : BaseActivity() {

    override val mLayout: Int = R.layout.activity_detail_map

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var mDetailMapViewModel: DetailMapViewModel

    override fun initializeViewModels() {
        mDetailMapViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailMapViewModel::class.java)
    }

    override fun initializeUI() {
        if (intent.hasExtra(EXTRA_CHURCH)) {
            mDetailMapViewModel.setChurchInformation(intent.extras.get(EXTRA_CHURCH) as Church)
        }
        setSupportActionBar(toolbar_detail)
        toolbar_detail.title = getString(R.string.detail_map_activity_title)
        toolbar_detail.setNavigationOnClickListener { finish() }
    }

    override fun observeLiveData() {
        mDetailMapViewModel.getChurch().observe(this, Observer {
            showChurchInformation(it!!)
        })
    }


    fun showChurchInformation(church: Church) {

        church_name.text = String.format(getString(R.string.map_label_church), church.mName)

    }

}
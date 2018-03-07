package com.effeta.miparroquiaandroid.common

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.viewmodel.ParishViewModel
import com.effeta.miparroquiaandroid.views.activities.SelectChurchActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_drawer_header.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class NavDrawerActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mParishViewModel: ParishViewModel

    abstract val mToolbarMenu: Int?

    abstract val mTitle: Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        supportActionBar?.title = getString(mTitle!!)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        mToolbarMenu?.let {
            menuInflater.inflate(mToolbarMenu!!, menu)
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_change_parish -> {
                startActivity(intentFor<SelectChurchActivity>()
                        .clearTop()
                        .singleTop())
                return true
            }
            R.id.nav_support -> {
                // handle click
                toast(R.string.feature_not_ready)
                return true
            }
        }
        return false
    }

    fun setNavDrawerInformation(parish: Parish?) {
        parish?.let {
            textview_email.text = parish.mEmail
            textview_name.text = parish.mName
            textview_phonenumber.text = parish.mPhone
            textview_schedule.text = parish.mSchedule
        }
    }

    override fun initializeViewModels() {
        mParishViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ParishViewModel::class.java)
    }

    override fun observeLiveData(isNewActivity: Boolean) {
        mParishViewModel.getParish().observe(this, Observer {
            setNavDrawerInformation(it)
        })
    }
}
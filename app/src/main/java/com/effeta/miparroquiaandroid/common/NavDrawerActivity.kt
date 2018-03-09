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
import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.models.Parish
import com.effeta.miparroquiaandroid.viewmodel.ParishViewModel
import com.effeta.miparroquiaandroid.views.activities.SelectChurchActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_drawer_header.view.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class NavDrawerActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mParishViewModel: ParishViewModel

    abstract val mToolbarMenu: Int?

    abstract val mTitle: Int?

    private lateinit var header: View

    abstract val mLayout: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayout)

        //Set the Nav Drawer
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        header = nav_view.getHeaderView(0)
        supportActionBar?.title = getString(mTitle!!)

        //Init the view models and UI
        initialize(false)

        //Set the Observer
        observeLiveDataToNavDawer(savedInstanceState == null)

    }

    private fun initialize(isNewActivity: Boolean) {
        initializeViewModelsNavDrawer()
        initializeViewModels()
        initializeUI()
    }

    abstract fun initializeUI()

    abstract fun initializeViewModels()

    private fun initializeViewModelsNavDrawer() {
        mParishViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ParishViewModel::class.java)
    }

    private fun observeLiveDataToNavDawer(isNewActivity: Boolean) {
        mParishViewModel.getParish().observe(this, Observer<Parish> {
            setNavDrawerInformation(it!!)
        })
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

    private fun setNavDrawerInformation(parish: Parish?) {
        parish?.let {
            header.textview_email?.text = parish.mEmail
            header.textview_name?.text = parish.mName
            header.textview_phonenumber?.text = parish.mPhone
            header.textview_schedule?.text = parish.mSchedule
        }
    }
}

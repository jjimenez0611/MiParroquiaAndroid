package com.effeta.miparroquiaandroid.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MenuItem
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.NavDrawerActivity
import com.effeta.miparroquiaandroid.viewmodel.ChurchListViewModel
import com.effeta.miparroquiaandroid.views.adapters.ViewPagerAdapter
import com.effeta.miparroquiaandroid.views.fragments.AnnouncementsFragment
import com.effeta.miparroquiaandroid.views.fragments.ChurchMapFragment
import com.effeta.miparroquiaandroid.views.fragments.EucharistFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : NavDrawerActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_announcements -> {
                viewpager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_eucharist -> {
                viewpager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_church_map -> {
                viewpager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val mOnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            if (prevMenuItem != null) {
                prevMenuItem!!.isChecked = false
            } else {
                navigation.menu.getItem(0).isChecked = false
            }
            Log.d("page", "onPageSelected: " + position)
            navigation.menu.getItem(position).isChecked = true
            prevMenuItem = navigation.menu.getItem(position)
        }

        override fun onPageSelected(position: Int) {
        }

    }

    private var prevMenuItem: MenuItem? = null

    private lateinit var mChurchViewModel: ChurchListViewModel

    override val mLayout: Int = R.layout.activity_main

    override val mToolbarMenu: Int? = null

    override val mTitle: Int? = R.string.app_name

    override fun initializeViewModels() {
        mChurchViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChurchListViewModel::class.java)
    }

    override fun initializeUI() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewpager.addOnPageChangeListener(mOnPageChangeListener)
    }

    override fun observeLiveData() {
        mChurchViewModel.getChurches().observe(this, Observer {
            setupViewPager(viewpager)
        })
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AnnouncementsFragment())
        adapter.addFragment(EucharistFragment())
        adapter.addFragment(ChurchMapFragment())
        viewPager.adapter = adapter
    }
}

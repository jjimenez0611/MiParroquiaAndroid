package com.effeta.miparroquiaandroid.views.activities

import android.arch.lifecycle.Observer
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.viewmodel.AnnouncementViewModel
import com.effeta.miparroquiaandroid.views.adapters.ViewPagerAdapter
import com.effeta.miparroquiaandroid.views.common.BaseActivity
import com.effeta.miparroquiaandroid.views.common.createViewModel
import com.effeta.miparroquiaandroid.views.common.unsafeLazy
import com.effeta.miparroquiaandroid.views.fragments.AnnouncementsFragment
import com.effeta.miparroquiaandroid.views.fragments.ChurchMapFragment
import com.effeta.miparroquiaandroid.views.fragments.EucharistFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_announcements -> {
//                message.setText(R.string.title_announcements)
                viewpager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_eucharist -> {
                viewpager.currentItem = 1
//                message.setText(R.string.title_eucharist)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_church_map -> {
                viewpager.currentItem = 2
//                message.setText(R.string.title_church_map)
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

    override val mLayout: Int = R.layout.activity_main

    private lateinit var mAnnouncementViewModel : AnnouncementViewModel

    override fun initializeViewModels() {
        mAnnouncementViewModel = createViewModel(this, AnnouncementViewModel::class.java)
    }

    override fun initializeUI() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewpager.addOnPageChangeListener(mOnPageChangeListener)
        setupViewPager(viewpager)
    }

    override fun observeLiveData(isNewActivity: Boolean) {
        if (isNewActivity) {
            mAnnouncementViewModel.setChurch("SGIRn6F4Q22haQv1NOEg")
        }
        mAnnouncementViewModel.announcementsLiveData.observe(this, Observer {
            Log.d("Success", it.toString())
        })
        mAnnouncementViewModel.isLoadingLiveData.observe(this, Observer {
            if (it!!) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        })
        mAnnouncementViewModel.throwableLiveData.observe(this, Observer {
            progress.visibility = View.GONE
            if (it != null) {

            } else {

            }
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

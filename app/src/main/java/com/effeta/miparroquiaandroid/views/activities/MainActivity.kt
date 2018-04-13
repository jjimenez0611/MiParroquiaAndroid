package com.effeta.miparroquiaandroid.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MenuItem
import com.effeta.miparroquiaandroid.R
import com.effeta.miparroquiaandroid.common.NavDrawerActivity
import com.effeta.miparroquiaandroid.rxBusEvents.ChangeThemeEvent
import com.effeta.miparroquiaandroid.utils.RXBus
import com.effeta.miparroquiaandroid.viewmodel.DataViewModel
import com.effeta.miparroquiaandroid.views.adapters.ViewPagerAdapter
import com.effeta.miparroquiaandroid.views.fragments.AnnouncementsFragment
import com.effeta.miparroquiaandroid.views.fragments.EucharistFragment
import com.effeta.miparroquiaandroid.views.fragments.ParishMapFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert


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

            navigation.menu.getItem(position).isChecked = true
            prevMenuItem = navigation.menu.getItem(position)
        }

        override fun onPageSelected(position: Int) {
            Log.d("page", "onPageSelected: $position")
        }
    }

    private var prevMenuItem: MenuItem? = null

    private lateinit var mDataViewModel: DataViewModel

    override val mLayout: Int = R.layout.activity_main

    override val mToolbarMenu: Int? = null

    override val mTitle: Int? = R.string.app_name

    override fun initializeViewModels() {
        mDataViewModel = ViewModelProviders.of(this, viewModelFactory).get(DataViewModel::class.java)
    }

    override fun initializeUI() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewpager.addOnPageChangeListener(mOnPageChangeListener)
    }

    override fun observeLiveData() {

        // Listen for changeThemeEvent
        val disposal = RXBus.listen(ChangeThemeEvent::class.java).subscribe({
            alert(it.message) {
                isCancelable = false
                positiveButton(R.string.yes) {
                    finish()
                    startActivity(intent)
                }
            }.show()
        })

        disposal.dispose()

        mDataViewModel.getData().observe(this, Observer {
            if (it!!) {
                setupViewPager(viewpager)
            }
        })
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AnnouncementsFragment())
        adapter.addFragment(EucharistFragment())
        adapter.addFragment(ParishMapFragment())
        viewPager.adapter = adapter
    }

}

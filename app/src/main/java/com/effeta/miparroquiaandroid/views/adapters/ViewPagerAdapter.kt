package com.effeta.miparroquiaandroid.views.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.effeta.miparroquiaandroid.views.common.BaseFragment

/**
 * Created by aulate on 1/2/18.
 */
public class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val fragments = ArrayList<BaseFragment>()

    override fun getItem(position: Int): BaseFragment? {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(f: BaseFragment) {
        fragments.add(f)
    }

}
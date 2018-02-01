package com.effeta.miparroquiaandroid

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_announcements -> {
                message.setText(R.string.title_announcements)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_eucharist -> {
                message.setText(R.string.title_eucharist)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_church_map -> {
                message.setText(R.string.title_church_map)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

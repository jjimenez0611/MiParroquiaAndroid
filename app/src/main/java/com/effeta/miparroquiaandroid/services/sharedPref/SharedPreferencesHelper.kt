package com.effeta.miparroquiaandroid.services.sharedPref

import android.content.Context
import javax.inject.Inject

/**
 * Created by aulate on 5/3/18.
 */
class SharedPreferencesHelper @Inject constructor(private val context: Context) {

    private val filename: String = "MiParroquiaPref"
    private val mParishKey: String = "PARISH_SELECTED"

    fun storeParishKey(parishKey: String) {
        val sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(mParishKey, parishKey)
        editor.apply()
    }

    fun getParishKey(): String? {
        val sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE)
        var key = sharedPreferences.getString(mParishKey, "")
        return key
    }

}
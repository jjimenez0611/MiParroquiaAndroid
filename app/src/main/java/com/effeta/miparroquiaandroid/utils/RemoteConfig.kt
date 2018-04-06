package com.effeta.miparroquiaandroid.utils

import android.app.Activity
import android.util.Log
import com.effeta.miparroquiaandroid.BuildConfig
import com.effeta.miparroquiaandroid.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


object RemoteConfig {


    fun getRemoteConfig() {
        // Get Remote Config instance.
        // [START get_remote_config_instance]
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        // [END get_remote_config_instance]

        // Create a Remote Config Setting to enable developer mode, which you can use to increase
        // the number of fetches available per hour during development. See Best Practices in the
        // README for more information.
        // [START enable_dev_mode]
        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()
        mFirebaseRemoteConfig.setConfigSettings(configSettings)
        // [END enable_dev_mode]

        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults)

        var cacheExpiration: Long = 3

        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.info.configSettings.isDeveloperModeEnabled) {
            cacheExpiration = 3
        }

        // [START fetch_config_with_callback]
        // cacheExpirationSeconds is set to cacheExpiration here, indicating the next fetch request
        // will use fetch data from the Remote Config service, rather than cached parameter values,
        // if cached parameter values are more than cacheExpiration seconds old.
        // See Best Practices in the README for more information.

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener( { task ->
                    if (task.isSuccessful) {
                        // After config data is successfully fetched, it must be activated before newly fetched
                        // values are returned.
                        mFirebaseRemoteConfig.activateFetched()
                    }
                    // displayWelcomeMessage()
                })
        // [END fetch_config_with_callback]

    }


    fun getTheme():Int{
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val value = mFirebaseRemoteConfig.getString("theme_app")

        when(value){
            "advent" -> return R.style.AppThemeAdvent
            "lent" -> return R.style.AppThemeLent
            "easter" -> return R.style.AppThemeEaster
            "festive" -> return R.style.AppThemeFestive
        }
        return R.style.AppTheme
    }

    fun getThemeCollapsingToolbar():Int{
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val value = mFirebaseRemoteConfig.getString("theme_app")

        when(value){
            "advent" -> return R.color.colorPrimaryAdvent
            "lent" -> return R.color.colorPrimaryLent
            "easter" -> return R.color.colorPrimaryEaster
            "festive" -> return R.color.colorPrimaryFestive
        }
        return R.style.CollapsingToolbarLayoutOrdinary
    }


}
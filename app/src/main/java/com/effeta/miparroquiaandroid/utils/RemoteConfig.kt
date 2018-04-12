package com.effeta.miparroquiaandroid.utils

import com.effeta.miparroquiaandroid.BuildConfig
import com.effeta.miparroquiaandroid.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


object RemoteConfig {

    private const val LENT_VALUE = "lent"
    private const val ADVENT_VALUE = "advent"
    private const val EASTER_VALUE = "easter"
    private const val FESTIVE_VALUE = "festive"
    private const val PARAMETER_THEME_VALUE = "theme_app"

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

        var cacheExpiration: Long = 3600

        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.info.configSettings.isDeveloperModeEnabled) {
            cacheExpiration = 0
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
        val value = mFirebaseRemoteConfig.getString(PARAMETER_THEME_VALUE)

        when(value){
            ADVENT_VALUE-> return R.style.AppThemeAdvent
            LENT_VALUE -> return R.style.AppThemeLent
            EASTER_VALUE -> return R.style.AppThemeEaster
            FESTIVE_VALUE -> return R.style.AppThemeFestive
        }
        return R.style.AppTheme
    }

    fun getPrimaryColor():Int{
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val value = mFirebaseRemoteConfig.getString(PARAMETER_THEME_VALUE)

        when(value){
            ADVENT_VALUE -> return R.color.colorPrimaryAdvent
            LENT_VALUE -> return R.color.colorPrimaryLent
            EASTER_VALUE -> return R.color.colorPrimaryEaster
            FESTIVE_VALUE -> return R.color.colorPrimaryFestive
        }
        return R.color.colorPrimary
    }

    fun getPrimaryLightColor():Int{
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val value = mFirebaseRemoteConfig.getString(PARAMETER_THEME_VALUE)

        when(value){
            ADVENT_VALUE -> return R.color.colorPrimaryLightAdvent
            LENT_VALUE -> return R.color.colorPrimaryLightLent
            EASTER_VALUE -> return R.color.colorPrimaryLightEaster
            FESTIVE_VALUE -> return R.color.colorPrimaryLightFestive
        }
        return R.color.colorPrimaryLight
    }


}
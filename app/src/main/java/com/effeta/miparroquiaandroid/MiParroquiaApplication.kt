package com.effeta.miparroquiaandroid

import com.effeta.miparroquiaandroid.di.DaggerAppComponent
import com.effeta.miparroquiaandroid.utils.RemoteConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by aulate on 16/2/18.
 */
class MiParroquiaApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        RemoteConfig.getRemoteConfig()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}
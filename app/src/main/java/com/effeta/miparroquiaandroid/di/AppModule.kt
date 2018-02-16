package com.effeta.miparroquiaandroid.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by aulate on 16/2/18.
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun provideContext(application: Application): Context
}
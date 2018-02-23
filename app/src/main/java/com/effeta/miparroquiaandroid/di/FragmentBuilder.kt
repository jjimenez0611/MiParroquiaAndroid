package com.effeta.miparroquiaandroid.di

import com.effeta.miparroquiaandroid.views.fragments.AnnouncementsFragment
import com.effeta.miparroquiaandroid.views.fragments.ChurchMapFragment
import com.effeta.miparroquiaandroid.views.fragments.EucharistFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by aulate on 16/2/18.
 */
@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector()
    abstract fun bindAnnouncementFragment() : AnnouncementsFragment

    @ContributesAndroidInjector()
    abstract fun bindChurchMapFragment() : ChurchMapFragment

    @ContributesAndroidInjector()
    abstract fun bindEucharistFragment() : EucharistFragment

}
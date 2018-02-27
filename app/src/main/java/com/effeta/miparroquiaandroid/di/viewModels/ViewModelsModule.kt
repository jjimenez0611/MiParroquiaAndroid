package com.effeta.miparroquiaandroid.di.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.effeta.miparroquiaandroid.viewmodel.AnnouncementViewModel
import com.effeta.miparroquiaandroid.viewmodel.ChurchMapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnnouncementViewModel::class)
    abstract fun bindAnnouncementViewModel(announcementViewModel: AnnouncementViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChurchMapViewModel::class)
    abstract fun bindChurchMapViewModel(churchMapViewModel: ChurchMapViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
package com.effeta.miparroquiaandroid.di.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.effeta.miparroquiaandroid.viewmodel.AnnouncementViewModel
import com.effeta.miparroquiaandroid.viewmodel.ChurchMapViewModel
import com.effeta.miparroquiaandroid.viewmodel.MapViewModel
import com.effeta.miparroquiaandroid.viewmodel.ParishViewModel
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
    @IntoMap
    @ViewModelKey(ParishViewModel::class)
    abstract fun bindParishViewModel(parishViewModel: ParishViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(mapViewModel: MapViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
package com.effeta.miparroquiaandroid.di.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.effeta.miparroquiaandroid.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnnouncementListViewModel::class)
    abstract fun bindAnnouncementViewModel(announcementListViewModel: AnnouncementListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChurchListViewModel::class)
    abstract fun bindChurchMapViewModel(churchListViewModel: ChurchListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ParishViewModel::class)
    abstract fun bindParishViewModel(parishViewModel: ParishViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(mapViewModel: MapViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChurchViewModel::class)
    abstract fun bindDetailMapViewModel(mapViewModel: ChurchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EucharistListViewModel::class)
    abstract fun bindEucharistViewModel(eucharistListViewModel: EucharistListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
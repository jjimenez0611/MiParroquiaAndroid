package com.effeta.miparroquiaandroid.di.viewModels

import com.effeta.miparroquiaandroid.viewmodel.*
import dagger.Component

@Component(modules = arrayOf(
        ViewModelsModule::class
))
interface ViewModelComponent {

    fun inject(announcementListViewModel: AnnouncementListViewModel)

    fun inject(churchListViewModel: ChurchListViewModel)

    fun inject(parishViewModel: ParishViewModel)

    fun inject(mapViewModel: MapViewModel)

    fun inject(churchViewModel: ChurchViewModel)

    fun inject(dataViewModel: DataViewModel)

}
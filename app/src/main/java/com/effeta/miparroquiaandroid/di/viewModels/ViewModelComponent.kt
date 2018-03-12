package com.effeta.miparroquiaandroid.di.viewModels

import com.effeta.miparroquiaandroid.viewmodel.*
import dagger.Component

@Component(modules = arrayOf(
        ViewModelsModule::class
))
interface ViewModelComponent {

    fun inject(announcementViewModel: AnnouncementViewModel)

    fun inject(churchMapViewModel: ChurchMapViewModel)

    fun inject(parishViewModel: ParishViewModel)

    fun inject(mapViewModel: MapViewModel)

    fun inject(detailMapViewModel: DetailMapViewModel)

}
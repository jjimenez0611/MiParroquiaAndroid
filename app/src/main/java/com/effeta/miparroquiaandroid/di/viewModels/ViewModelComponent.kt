package com.effeta.miparroquiaandroid.di.viewModels

import com.effeta.miparroquiaandroid.viewmodel.AnnouncementViewModel
import com.effeta.miparroquiaandroid.viewmodel.ChurchMapViewModel
import dagger.Component

@Component(modules = arrayOf(
        ViewModelsModule::class
))
interface ViewModelComponent {

    fun inject(announcementViewModel: AnnouncementViewModel)

    fun inject(churchMapViewModel: ChurchMapViewModel)

}
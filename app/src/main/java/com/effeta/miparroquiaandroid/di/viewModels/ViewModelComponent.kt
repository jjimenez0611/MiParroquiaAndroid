package com.effeta.miparroquiaandroid.di.viewModels

import com.effeta.miparroquiaandroid.viewmodel.AnnouncementViewModel
import dagger.Component

@Component( modules = arrayOf(
        ViewModelsModule::class
))
interface ViewModelComponent {

    fun inject( mainViewModel: AnnouncementViewModel )

}
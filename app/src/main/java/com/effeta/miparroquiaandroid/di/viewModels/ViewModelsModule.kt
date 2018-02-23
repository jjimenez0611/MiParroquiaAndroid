package com.effeta.miparroquiaandroid.di.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.effeta.miparroquiaandroid.viewmodel.AnnouncementViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey( AnnouncementViewModel::class )
    abstract fun bindMainViewModel( mainViewModel: AnnouncementViewModel ) : ViewModel

    @Binds
    abstract fun bindViewModelFactory( factory: ViewModelFactory ) : ViewModelProvider.Factory

}
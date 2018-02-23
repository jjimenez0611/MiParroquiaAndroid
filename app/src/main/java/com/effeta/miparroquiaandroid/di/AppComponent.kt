package com.effeta.miparroquiaandroid.di

import android.app.Application
import com.effeta.miparroquiaandroid.MiParroquiaApplication
import com.effeta.miparroquiaandroid.di.viewModels.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by aulate on 16/2/18.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ViewModelsModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: MiParroquiaApplication)

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
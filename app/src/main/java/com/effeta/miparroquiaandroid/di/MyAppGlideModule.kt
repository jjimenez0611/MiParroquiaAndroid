package com.effeta.miparroquiaandroid.di

import android.content.Context
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.storage.StorageReference
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream


/**
 * Created by jjimenez on 3/22/18.
 */
@GlideModule
class MyAppGlideModule : AppGlideModule() {
    fun registerComponents(context: Context, registry: Registry) {
        // Register FirebaseImageLoader to handle StorageReference
        registry.append(StorageReference::class.java, InputStream::class.java,
                FirebaseImageLoader.Factory())
    }
}
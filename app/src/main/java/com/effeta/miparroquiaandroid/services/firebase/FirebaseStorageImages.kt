package com.effeta.miparroquiaandroid.services.firebase

import com.effeta.miparroquiaandroid.common.IMAGES_STORAGE_REFERENCE
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

/**
 * Created by jjimenez on 3/22/18.
 */
class FirebaseStorageImages @Inject constructor() {
    fun getReferenceStorageImage(churchKey: String): StorageReference {
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child(IMAGES_STORAGE_REFERENCE)
        return imagesRef.child(churchKey+".jpg")
    }
}
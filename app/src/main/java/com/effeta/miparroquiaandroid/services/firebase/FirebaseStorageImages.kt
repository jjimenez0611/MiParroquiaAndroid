package com.effeta.miparroquiaandroid.services.firebase

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

/**
 * Created by jjimenez on 3/22/18.
 */
class FirebaseStorageImages @Inject constructor() {
    fun getReferenceStorageImage(churchKey: String): StorageReference {
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child("images/churches/")
        return imagesRef.child(churchKey)
    }
}
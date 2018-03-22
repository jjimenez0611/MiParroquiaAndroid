package com.effeta.miparroquiaandroid.services.firebase

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

/**
 * Created by jjimenez on 3/22/18.
 */
class FirebaseStorageImages @Inject constructor() {

    private val churchesImagesKey = "churches/images/churches/"
    private val storageRef = FirebaseStorage.getInstance().reference


    fun getImageChurch(key:String){
        storageRef.child("$churchesImagesKey$key.jpg")

    }


}
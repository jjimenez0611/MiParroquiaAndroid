package com.effeta.miparroquiaandroid

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.effeta.miparroquiaandroid.models.Announcement
import com.effeta.miparroquiaandroid.services.firebase.FirebaseAnnouncement
import com.google.firebase.FirebaseApp
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import java.util.*


/**
 * Created by jjimenez on 2/22/18.
 */
@RunWith(AndroidJUnit4::class)
class FirebaseAnnouncementUnitTest {

    var mock = Mockito.mock<FirebaseAnnouncement>(FirebaseAnnouncement::class.java)
    private val announcementList = ArrayList<Announcement>()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()!!

    @Before
    fun setup() {
        if (FirebaseApp.getApps(InstrumentationRegistry.getContext()).isEmpty()) {
            FirebaseApp.initializeApp(InstrumentationRegistry.getContext());
        }
        val announcement1 = Announcement("key1", "Announcement 1", "Announcement Description 1", "Church 1", "Image 1", "Type 1", Date(), Date(), Date())
        val announcement2 = Announcement("key2", "Announcement 2", "Announcement Description 2", "Church 2", "Image 2", "Type 2", Date(), Date(), Date())
        announcementList.add(announcement1)
        announcementList.add(announcement2)
        Mockito.`when`(mock.getAllAnnouncements()).thenReturn(Observable.just(announcementList))
    }

    @Test
    fun canGetAnnouncementsList() {
        val userObservable = mock.getAllAnnouncements()
        val userTestObserver = TestObserver<List<Announcement>>()
        userObservable.subscribe(userTestObserver)
        userTestObserver.assertValue(announcementList)
    }

}
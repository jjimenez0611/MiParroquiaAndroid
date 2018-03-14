package com.effeta.miparroquiaandroid.utils

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.net.Uri
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * Created by jjimenez on 3/7/18.
 */
object MapUtils {

    fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor {
        val canvas = Canvas()
        val bitmap: Bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    fun getIntentToOpenGoogleMaps(latitude: Double?, longitude: Double?, description:String):Intent{

        val uriBegin = "geo:$latitude,$longitude"
        val query = latitude.toString() + "," + longitude.toString() + "(" + description + ")"
        val encodedQuery = Uri.encode(query)
        val uriString = "$uriBegin?q=$encodedQuery&z=16"
        val uri = Uri.parse(uriString)
        return Intent(android.content.Intent.ACTION_VIEW, uri)
    }

}
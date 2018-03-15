package com.effeta.miparroquiaandroid.common

import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View


/**
 * Created by aulate on 14/3/18.
 */
class ItemClickListener(recyclerView: RecyclerView, private val mListener: ClickListener?) : RecyclerView.OnItemTouchListener {

    internal var mGestureDetector: GestureDetector

    interface ClickListener {
        fun onItemClick(view: View, position: Int)
        fun onLongItemClick(view: View?, position: Int)
    }

    init {
        mGestureDetector = GestureDetector(recyclerView.context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val child = recyclerView.findChildViewUnder(e.x, e.y)
                if (child != null && mListener != null) {
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
            return true
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}
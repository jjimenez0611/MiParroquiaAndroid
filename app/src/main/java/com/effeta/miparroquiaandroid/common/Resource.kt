package com.effeta.miparroquiaandroid.common

/** -*- coding: utf-8 -*-
 * This file was created by
 * @Author: aulate
 * @Date:   26/3/18
 */
//a generic class that describes a data with a status
class Resource<out T> private constructor(
        val status: Status,
        val data: T?,
        val message: String?) {
    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource<T>(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource<T>(Status.LOADING, data, null)
        }
    }
}

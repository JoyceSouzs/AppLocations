package com.jmfs.locations.repository

import com.jmfs.locations.entity.Place

interface PlaceRepository {

    fun getPlacesType(location : String, radius: Int, key: String, type: String,
                  callback: Callback<List<Place>>)

    fun getPlaces(location : String, radius: Int, key: String, callback: Callback<List<Place>>)

    interface Callback<T> {
        fun onSuccess(data: T)
        fun onError(message: String?)
    }
}
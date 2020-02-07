package com.jmfs.locations.api

import com.jmfs.locations.entity.ResultPlace
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesServices {

    @GET("place/nearbysearch/json?")
    fun getPlaceType(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") key: String
    ): Call<ResultPlace>

    @GET("place/nearbysearch/json?")
    fun getPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("key") key: String
    ): Call<ResultPlace>
}

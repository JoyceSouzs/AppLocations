package com.jmfs.locations.repository

import android.util.Log
import com.jmfs.locations.api.PlacesServices
import com.jmfs.locations.entity.Place
import com.jmfs.locations.entity.ResultPlace
import com.jmfs.locations.util.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceRepositoryImpl(private val placesServices: PlacesServices) : PlaceRepository {

    override fun getPlacesType(location: String, radius: Int, key: String, type: String,
                               callback: PlaceRepository.Callback<List<Place>>) {
        val call = placesServices.getPlaceType(location, Constant.radius,
                type , Constant.key)

        call.enqueue(object : Callback<ResultPlace> {
            override fun onFailure(call: Call<ResultPlace>, t: Throwable) {
                Log.d("onFailure",t.localizedMessage)
                callback.onError(t.localizedMessage)

            }

            override fun onResponse(call: Call<ResultPlace>, response: Response<ResultPlace>) {
                if (response.isSuccessful) {
                    val url = call.request().url()
                    Log.d("url", url.toString())
                    callback.onSuccess(response.body()?.listResultPlace ?: listOf())
                }
            }
        })
    }

    override fun getPlaces(location: String,radius: Int, key: String,
                           callback: PlaceRepository.Callback<List<Place>>) {

        val call = placesServices.getPlaces(location, Constant.radius
            , Constant.key)
        call.enqueue(object : Callback<ResultPlace> {
            override fun onFailure(call: Call<ResultPlace>, t: Throwable) {
                Log.d("onFailure", t.localizedMessage)
                callback.onError(t.localizedMessage)

            }

            override fun onResponse(call: Call<ResultPlace>, response: Response<ResultPlace>) {
                if (response.isSuccessful) {
                    val url = call.request().url()
                    Log.d("url", url.toString())
                    callback.onSuccess(response.body()?.listResultPlace ?: listOf())
                }
            }
        })
    }
}
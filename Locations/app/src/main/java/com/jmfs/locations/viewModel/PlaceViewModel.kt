package com.jmfs.locations.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.jmfs.locations.entity.Place
import com.jmfs.locations.repository.PlaceRepository
import com.jmfs.locations.util.Constant

val TYPES = listOf(
    Constant.ALL,
    Constant.RESTAURANT,
    Constant.AIRPORT,
    Constant.NIGHTCLUB,
    Constant.SUPERMARKET,
    Constant.SHOPPING
)

class PlaceViewModel(private val iPlaceRepository: PlaceRepository): ViewModel(){

    val listPlaces = MutableLiveData<List<Place>>()
    val loading = MutableLiveData<Boolean>()

    private var selectedType = Constant.ALL

    fun setSelectedType(selectedIndex: Int, context: Context) {
        selectedType = TYPES[selectedIndex]
        loadPlaces(context)
    }

    fun loadPlaces(context: Context) {
        loading.value = true
        LocationServices.getFusedLocationProviderClient(context).lastLocation
            .addOnCompleteListener(OnCompleteListener {
                if (!it.isSuccessful) {
                    loading.value = false
                    return@OnCompleteListener
                }

                val location = Constant.location

                if (selectedType == Constant.ALL) {
                    getPlaces(location)
                } else {
                    getPlacesByType(location)
                }
            })
    }

    private fun getPlaces(location: String) {
        iPlaceRepository.getPlaces(location,
            Constant.radius, Constant.key, object : PlaceRepository.Callback<List<Place>> {
                override fun onSuccess(data: List<Place>) {
                    listPlaces.value = data
                    loading.value = false
                }

                override fun onError(message: String?) {
                    listPlaces.value = listOf()
                    loading.value = false
                }
            })
    }

    private fun getPlacesByType(location: String) {
        loading.value = true
        iPlaceRepository.getPlacesType(location, Constant.radius, Constant.key,
            selectedType, object : PlaceRepository.Callback<List<Place>> {
                override fun onSuccess(data: List<Place>) {
                    listPlaces.value = data
                    loading.value = false
                }

                override fun onError(message: String?) {
                    listPlaces.value = listOf()
                    loading.value = false
                }
            })
    }
}
package com.jmfs.locations.entity

import com.google.gson.annotations.SerializedName

data class ResultPlace(
    @SerializedName("results")
    var listResultPlace: List<Place>,

    @SerializedName("status")
    var status: String)
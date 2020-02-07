package com.jmfs.locations.entity

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("name")
    var name: String,
    @SerializedName("vicinity")
    var address: String


)


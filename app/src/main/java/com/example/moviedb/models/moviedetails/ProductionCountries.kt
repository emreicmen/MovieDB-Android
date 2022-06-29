package com.example.moviedb.models.moviedetails

import com.google.gson.annotations.SerializedName

data class ProductionCountries (
    @SerializedName("iso_3166_1")
    val country : String,
    val name : String
    )

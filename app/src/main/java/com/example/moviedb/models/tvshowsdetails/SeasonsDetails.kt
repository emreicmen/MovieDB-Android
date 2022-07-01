package com.example.moviedb.models.tvshowsdetails

data class SeasonsDetails (
    val air_date : String?,
    val name : String,
    val poster_path : String?,
    val overview : String?,
    val season_number : Int,
    val episode_count : Int,
)
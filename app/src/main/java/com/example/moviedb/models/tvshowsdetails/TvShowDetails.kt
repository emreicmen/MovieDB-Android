package com.example.moviedb.models.tvshowsdetails

import com.example.moviedb.models.moviedetails.GenreDetails

data class TvShowDetails (
    val id : Int,
    val title : String?,
    val backdrop_path : String?,
    val poster_path : String?,
    val overview : String?,
    val vote_average : Double?,
    val vote_count : Int,
    val original_language : String,
    val original_name : String,
    val genres : List<GenreDetails> = mutableListOf()
)
package com.example.moviedb.models.movie

data class Movie (
    val id : String?,
    val title : String?,
    val poster_path : String?,
    val release_date : String?,
    val overview : String?,
    val vote_average : Double?
    )
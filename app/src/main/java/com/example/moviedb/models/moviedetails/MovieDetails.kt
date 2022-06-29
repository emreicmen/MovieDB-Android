package com.example.moviedb.models.moviedetails

data class MovieDetails (
    val id : Int,
    val runtime : Int?,
    val title : String?,
    val backdrop_path : String?,
    val poster_path : String?,
    val release_date : String?,
    val overview : String?,
    val vote_average : Double?,
    val vote_count : Int,
    val adult : Boolean,
    val homepage : String?,
    val original_language : String,
    val status : String,
    val tagline : String?,
    val genres : List<GenreDetails> = mutableListOf(),
    val production_companies : List<ProductionCompanies> = mutableListOf(),
    val production_countries : List<ProductionCountries> = mutableListOf()

)
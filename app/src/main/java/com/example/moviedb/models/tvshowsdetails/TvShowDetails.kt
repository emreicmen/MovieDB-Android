package com.example.moviedb.models.tvshowsdetails

import com.example.moviedb.models.moviedetails.GenreDetails
import com.example.moviedb.models.moviedetails.ProductionCompanies

data class TvShowDetails (
    val id : Int,
    val name : String?,
    val backdrop_path : String?,
    val poster_path : String?,
    val overview : String?,
    val vote_average : Double?,
    val vote_count : Int,
    val original_language : String,
    val original_name : String,
    val in_production : Boolean,
    val tagline : String,
    val first_air_date : String,
    val popularity : Double,
    val number_of_seasons : Int,
    val number_of_episodes : Int,

    val genres : List<GenreDetails> = mutableListOf(),
    val production_companies : List<ProductionCompanies> = mutableListOf()
)
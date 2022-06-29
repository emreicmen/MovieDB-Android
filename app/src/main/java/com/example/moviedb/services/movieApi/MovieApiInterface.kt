package com.example.moviedb.services.movieApi

import com.example.moviedb.models.movie.MovieResponse
import com.example.moviedb.models.moviedetails.GenreDetails
import com.example.moviedb.models.moviedetails.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiInterface {
    @GET("/3/movie/popular?api_key=c6499f37e0c8f222da06a575732cdf73")
    fun getMovieList( ) : Call<MovieResponse>

    @GET("/3/movie/{movie_id}?api_key=c6499f37e0c8f222da06a575732cdf73")
    fun getMovieDetails(@Path("movie_id") string: String)  : Call<MovieDetails>

    @GET("/3/movie/{movie_id}?api_key=c6499f37e0c8f222da06a575732cdf73")
    fun getSimilarMovies(@Path("movie_id") string: String)  : Call<MovieResponse>

}
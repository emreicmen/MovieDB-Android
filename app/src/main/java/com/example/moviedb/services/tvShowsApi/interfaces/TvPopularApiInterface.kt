package com.example.moviedb.services.tvShowsApi.interfaces

import com.example.moviedb.models.tvShows.TvShowsResponse
import com.example.moviedb.models.tvshowsdetails.TvShowDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TvPopularApiInterface {
    @GET("/3/tv/popular?api_key=c6499f37e0c8f222da06a575732cdf73")
    fun getTvShowsPopularList( ) : Call<TvShowsResponse>

    @GET("/3/tv/{tv_id}?api_key=c6499f37e0c8f222da06a575732cdf73")
    fun getTvShowsDetailsList(@Path("tv_id") string: String)  : Call<TvShowDetails>
}
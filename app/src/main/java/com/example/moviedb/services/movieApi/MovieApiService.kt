package com.example.moviedb.services.movieApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class MovieApiService {
    companion object{
        private const val  BASE_URL = "https://api.themoviedb.org/3/"
        private var retrofit : Retrofit? = null

        fun getInstance() : Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getLoggingInterceptor())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        private fun getLoggingInterceptor(): OkHttpClient{
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            return client
        }
    }
}
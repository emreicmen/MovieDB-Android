package com.example.moviedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.adapters.CompanyAdapter
import com.example.moviedb.adapters.GenreAdapter
import com.example.moviedb.adapters.MovieAdapter
import com.example.moviedb.models.movie.Movie
import com.example.moviedb.models.movie.MovieResponse
import com.example.moviedb.models.moviedetails.GenreDetails
import com.example.moviedb.models.moviedetails.MovieDetails
import com.example.moviedb.models.moviedetails.ProductionCompanies
import com.example.moviedb.services.movieApi.MovieApiInterface
import com.example.moviedb.services.movieApi.MovieApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailsActivity : AppCompatActivity() {

    private val  baseUrl = "https://api.themoviedb.org/3/"
    private  val imageUrl = "https://image.tmdb.org/t/p/w500/"
    private lateinit var takenMovieId : String

    private lateinit var genreAdapter: GenreAdapter
    private var genreList = mutableListOf<GenreDetails>()

    private lateinit var companyAdapter: CompanyAdapter
    private var companyList = mutableListOf<ProductionCompanies>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val intent = intent
        takenMovieId = intent.getStringExtra("takenmovieid").toString()

        loadData()
        initViews()
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MovieApiInterface::class.java)
        val call = service.getMovieDetails(takenMovieId)

        call.enqueue(object : Callback<MovieDetails>{
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        titleText.text= it.title.toString()
                        vote_average.text = it.vote_average.toString()
                        vote_count.text = "/" + it.vote_count.toString()
                        release_date.text = it.release_date.toString()
                        tagLineText.text = it.tagline.toString()
                        overview_text.text = it.overview.toString()
                        movie_link_text.text = it.homepage.toString()
                        Picasso.get().load(imageUrl + it.backdrop_path).into(back_poster)
                        Picasso.get().load(imageUrl + it.poster_path).into(front_poster)
                        languageText.text = it.original_language.uppercase()
                        lengthText.text = it.runtime.toString() + " Minutes long"

                        if (it.adult){
                            adult_imageView.setImageResource(R.drawable.adult_true)
                        }
                        else
                            adult_imageView.setImageResource(R.drawable.adult_false)
                        //Log.i("listeler", "Listeler ${it.genres}")
                        genreList.clear()
                        genreList.addAll(it.genres)
                        genreAdapter.notifyDataSetChanged()

                        companyList.clear()
                        companyList.addAll(it.production_companies)
                        companyAdapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun initViews() {
        genreAdapter = GenreAdapter(
            genres = genreList
        )
        genreRecycler.adapter = genreAdapter
        genreRecycler.setHasFixedSize(true)
        genreRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        companyAdapter = CompanyAdapter(
            companies = companyList
        )
        companiesRecycler.adapter = companyAdapter
        companiesRecycler.setHasFixedSize(true)
        companiesRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }
}



package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.adapters.CompanyAdapter
import com.example.moviedb.adapters.GenreAdapter
import com.example.moviedb.models.moviedetails.GenreDetails
import com.example.moviedb.models.moviedetails.ProductionCompanies
import com.example.moviedb.models.tvshowsdetails.TvShowDetails
import com.example.moviedb.services.tvShowsApi.interfaces.TvPopularApiInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_movie_details.back_poster
import kotlinx.android.synthetic.main.activity_movie_details.front_poster
import kotlinx.android.synthetic.main.activity_movie_details.genreRecycler
import kotlinx.android.synthetic.main.activity_movie_details.languageText
import kotlinx.android.synthetic.main.activity_movie_details.overview_text
import kotlinx.android.synthetic.main.activity_movie_details.titleText
import kotlinx.android.synthetic.main.activity_movie_details.vote_average
import kotlinx.android.synthetic.main.activity_movie_details.vote_count
import kotlinx.android.synthetic.main.activity_tv_shows_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TvShowsDetailActivity : AppCompatActivity() {

    private val  baseUrl = "https://api.themoviedb.org/3/"
    private  val imageUrl = "https://image.tmdb.org/t/p/w500/"
    private lateinit var takenShowId : String

    private lateinit var genreAdapter: GenreAdapter
    private var genreList = mutableListOf<GenreDetails>()

    private lateinit var companyAdapter: CompanyAdapter
    private var companyList = mutableListOf<ProductionCompanies>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_shows_detail)
        val intent = intent
        takenShowId = intent.getStringExtra("takenTvShowId").toString()

        loadData()
        initViews()
    }
    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TvPopularApiInterface::class.java)
        val call = service.getTvShowsDetailsList(takenShowId)

        call.enqueue(object : Callback<TvShowDetails> {
            override fun onResponse(call: Call<TvShowDetails>, response: Response<TvShowDetails>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        titleText.text= it.name.toString()
                        vote_average.text = it.vote_average.toString()
                        vote_count.text = "/" + it.vote_count.toString()
                        overview_text.text = it.overview.toString()
                        Picasso.get().load(imageUrl + it.backdrop_path).into(back_poster)
                        Picasso.get().load(imageUrl + it.poster_path).into(front_poster)
                        languageText.text = it.original_language.uppercase()

                        if (it.in_production){
                            statusText.text = "On stream"
                        }
                        else{
                            statusText.text = "Ended"
                        }
                        tagText.text = it.tagline
                        firstAirDateText.text = it.first_air_date
                        popularityText.text = it.popularity.toString()
                        infoText.text = "Seasons: " + it.number_of_seasons.toString() + " Episodes: " + it.number_of_episodes.toString()

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
            override fun onFailure(call: Call<TvShowDetails>, t: Throwable) {
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
        genreRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        companyAdapter = CompanyAdapter(
            companies = companyList
        )
        companiesRecyclerView.adapter = companyAdapter
        companiesRecyclerView.setHasFixedSize(true)
        companiesRecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
    }
}
package com.example.moviedb.fragments.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.MovieDetailsActivity
import com.example.moviedb.R
import com.example.moviedb.RecyclerItemClickListener
import com.example.moviedb.adapters.MovieAdapter
import com.example.moviedb.models.movie.Movie
import com.example.moviedb.models.movie.MovieResponse
import com.example.moviedb.services.movieApi.MovieApiInterface
import com.example.moviedb.services.movieApi.MovieApiService
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private var movieList = mutableListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getMovieData { movies : List<Movie> ->
            movieList.clear()
            movieList.addAll(movies)
            movieAdapter.notifyDataSetChanged()
        }
    }

    private fun initViews(){
        movieAdapter = MovieAdapter(
            movies = movieList,
            listener = object : RecyclerItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    Log.i("emre", "Clicked position: $position, id: ${view.id}, movie:${movieList[position].id}")
                    val intent = Intent(getActivity(), MovieDetailsActivity::class.java)
                    intent.putExtra("takenmovieid",movieList[position].id)
                    startActivity(intent);
                }
            }
        )
        moviesRecyclerView.adapter = movieAdapter
        moviesRecyclerView.setHasFixedSize(true)
        moviesRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun getMovieData(callback : (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.results)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

        })
    }
}
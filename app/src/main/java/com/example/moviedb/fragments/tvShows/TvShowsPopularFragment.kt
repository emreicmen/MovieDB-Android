package com.example.moviedb.fragments.tvShows

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedb.R
import com.example.moviedb.RecyclerItemClickListener
import com.example.moviedb.TvShowsDetailActivity
import com.example.moviedb.adapters.TvAdapter
import com.example.moviedb.models.tvShows.TvShows
import com.example.moviedb.models.tvShows.TvShowsResponse
import com.example.moviedb.services.tvShowsApi.interfaces.TvPopularApiInterface
import com.example.moviedb.services.tvShowsApi.services.TvShowsPopularApiService
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_tv_shows_popular.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowsPopularFragment : Fragment() {

    private lateinit var tvAdapter: TvAdapter
    private var tvShowsList = mutableListOf<TvShows>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_shows_popular, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getTvShowsData { movies : List<TvShows> ->
            tvShowsList.clear()
            tvShowsList.addAll(movies)
            tvAdapter.notifyDataSetChanged()
        }
    }

    private fun initViews(){
        tvAdapter = TvAdapter(
            tvShows = tvShowsList,
            listener = object : RecyclerItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    Log.i("emre", "Clicked position: $position, id: ${view.id}, movie:${tvShowsList[position].id}")
                    val intent = Intent(getActivity(), TvShowsDetailActivity::class.java)
                    intent.putExtra("takenTvShowId",tvShowsList[position].id)
                    startActivity(intent);
                }
            }
        )
        tvShowsPopularRecyclerView.adapter = tvAdapter
        tvShowsPopularRecyclerView.setHasFixedSize(true)
        tvShowsPopularRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun getTvShowsData(callback : (List<TvShows>) -> Unit){
        val apiService = TvShowsPopularApiService.getTvShowsPopularInstance().create(TvPopularApiInterface::class.java)
        apiService.getTvShowsPopularList().enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(call: Call<TvShowsResponse>, response: Response<TvShowsResponse>) {
                return callback(response.body()!!.results)
            }
            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
            }
        })
    }
}
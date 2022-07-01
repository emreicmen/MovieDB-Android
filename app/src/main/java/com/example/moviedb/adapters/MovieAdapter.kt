package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.RecyclerItemClickListener
import com.example.moviedb.models.movie.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val movies : MutableList<Movie>, private val listener: RecyclerItemClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view : View, listener : RecyclerItemClickListener) : RecyclerView.ViewHolder(view) {

        private  val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindMovie(movie : Movie){
            itemView.tv_title.text = movie.title
            itemView.tv_first_air_date.text = "Release date : " + movie.release_date
            itemView.overviewText.text = movie.overview
            itemView.voteText.text = movie.vote_average.toString()
            Picasso.get().load(IMAGE_BASE + movie.poster_path).into(itemView.tv_poster)
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(itemView, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MovieViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.RecyclerItemClickListener
import com.example.moviedb.models.movie.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.similar_movie_item.view.*

class SimilarMoviesAdapter(private val sMovies : MutableList<Movie>, private val listener: RecyclerItemClickListener) : RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMovieHolder>() {

    class SimilarMovieHolder(view : View, listener: RecyclerItemClickListener) : RecyclerView.ViewHolder(view) {

        private  val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindMovie(movie : Movie){
            itemView.similar_movie_name.text = movie.title
            Picasso.get().load(IMAGE_BASE + movie.poster_path).into(itemView.similar_movie_poster)
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(itemView, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.similar_movie_item,parent,false)
        return SimilarMovieHolder(v, listener)
    }

    override fun onBindViewHolder(holder: SimilarMovieHolder, position: Int) {
        holder.bindMovie(sMovies.get(position))
    }

    override fun getItemCount(): Int {
        return sMovies.size
    }
}
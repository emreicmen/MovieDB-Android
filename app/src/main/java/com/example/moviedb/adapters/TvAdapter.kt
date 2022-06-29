package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.RecyclerItemClickListener
import com.example.moviedb.models.tvShows.TvShows
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class TvAdapter(private val tvShows : MutableList<TvShows>, private val listener: RecyclerItemClickListener) : RecyclerView.Adapter<TvAdapter.TvViewHolder>(){

    class TvViewHolder(view : View, listener : RecyclerItemClickListener) : RecyclerView.ViewHolder(view) {

        private  val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun bindTvShows(tvShows : TvShows){
            itemView.tv_title.text = tvShows.name
            itemView.overviewText.text = tvShows.overview
            itemView.voteText.text = tvShows.vote_average.toString()
            Picasso.get().load(IMAGE_BASE + tvShows.poster_path).into(itemView.tv_poster);
        }
        init {
            itemView.setOnClickListener {
                listener.onItemClick(itemView, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.tv_item,parent,false)
        return TvViewHolder(v,listener)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bindTvShows(tvShows.get(position))
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
}
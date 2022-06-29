package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.models.moviedetails.GenreDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.genre_row_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.*

class GenreAdapter(private val genres: MutableList<GenreDetails>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    class GenreViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bindGenres(genre : GenreDetails){
            //itemView.genreIdText.text = genre.id.toString()
            itemView.genreNameText.text = genre.name.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.genre_row_item,parent,false)
        return GenreViewHolder(v)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bindGenres(genres.get(position))

    }

    override fun getItemCount(): Int {
        return genres.size
    }

}
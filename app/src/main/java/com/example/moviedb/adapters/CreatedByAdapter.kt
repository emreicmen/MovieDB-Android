package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.models.CreatedBy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.createdby_row_item.view.*
import kotlinx.android.synthetic.main.genre_row_item.view.*
import kotlinx.android.synthetic.main.production_companies_row_item.view.*

class CreatedByAdapter(private val createdBy: MutableList<CreatedBy>) : RecyclerView.Adapter<CreatedByAdapter.CreatedByViewHolder>() {

    class CreatedByViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private  val imageUrl = "https://image.tmdb.org/t/p/w500/"

        fun bindCreatedBy(createdBy : CreatedBy){
            //itemView.genreIdText.text = genre.id.toString()
            itemView.created_name_text.text = createdBy.name
            Picasso.get().load(imageUrl + createdBy.profile_path).into(itemView.posterImage);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatedByViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.createdby_row_item,parent,false)
        return CreatedByViewHolder(v)
    }

    override fun onBindViewHolder(holder: CreatedByViewHolder, position: Int) {
        holder.bindCreatedBy(createdBy.get(position))
    }

    override fun getItemCount(): Int {
        return createdBy.size
    }
}
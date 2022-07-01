package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.models.tvshowsdetails.SeasonsDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.seasons_row_item.view.*

class SeasonsAdapter(private val seasons: MutableList<SeasonsDetails>) : RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder>() {

    class SeasonsViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private  val imageUrl = "https://image.tmdb.org/t/p/w500/"

        fun bindSeasons(seasons : SeasonsDetails){
            if(seasons.name == "Specials"){
                itemView.seasonNameText.text = "Pilot Season"
            }
            else{
                itemView.seasonNameText.text = seasons.name
            }
            if(seasons.overview == ""){
                itemView.seasonOverviewText.text = "No documentary about this season"
            }
            else{
                itemView.seasonOverviewText.text = seasons.overview
            }

            if(seasons.poster_path == null){
                Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png").into(itemView.seasonsImage)
            }
            else{
                Picasso.get().load(imageUrl + seasons.poster_path).into(itemView.seasonsImage)

            }
            itemView.airDateText.text = "Season air date: " + seasons.air_date
            itemView.episodeCountText.text = "Episodes: " + seasons.episode_count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.seasons_row_item,parent,false)
        return SeasonsViewHolder(v)
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        holder.bindSeasons(seasons.get(position))
    }

    override fun getItemCount(): Int {
        return seasons.size
    }
}
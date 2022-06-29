package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.models.moviedetails.ProductionCompanies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.production_companies_row_item.view.*

class CompanyAdapter (private val companies : MutableList<ProductionCompanies>) : RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    class CompanyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private  val imageUrl = "https://image.tmdb.org/t/p/w500/"

        fun bindCompanies(company : ProductionCompanies){
            itemView.company_name_text.text = company.name
            Picasso.get().load(imageUrl + company.logo_path).into(itemView.logoImage);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.production_companies_row_item,parent,false)
        return CompanyViewHolder(v)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindCompanies(companies.get(position))
    }

    override fun getItemCount(): Int {
        return companies.size
    }
}
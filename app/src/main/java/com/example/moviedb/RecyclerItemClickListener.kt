package com.example.moviedb

import android.view.View

interface RecyclerItemClickListener {
    fun onItemClick(view: View, position: Int)
}
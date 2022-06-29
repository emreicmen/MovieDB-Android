package com.example.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviedb.adapters.ViewPagerAdapter
import com.example.moviedb.databinding.ActivityMainBinding
import com.example.moviedb.fragments.movies.MoviesFragment
import com.example.moviedb.fragments.tvShows.TvShowsPopularFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpTabs()
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MoviesFragment(),"Movies")
        adapter.addFragment(TvShowsPopularFragment(),"Tv Shows")

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}
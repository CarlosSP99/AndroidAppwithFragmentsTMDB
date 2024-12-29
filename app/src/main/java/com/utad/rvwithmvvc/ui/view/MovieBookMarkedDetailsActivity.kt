package com.utad.rvwithmvvc.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.utad.rvwithmvvc.R
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarked
import com.utad.rvwithmvvc.databinding.ActivityMovieDetailsBinding
import com.utad.rvwithmvvc.di.Constantes

class MovieBookMarkedDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadData()

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    private fun loadData() {
        val movie = intent.getParcelableExtra<MovieBookmarked>("movie")
        if (movie!=null){
            binding.tvTitle.text = movie.title
            binding.tvDate.text = movie.releaseDate
            binding.tvDescripcion.text = movie.overview
            Glide
                .with(binding.ivPoster.context)
                // necesario añadari en el manifest android:usesCleartextTraffic="true" dentro del application
                .load("${Constantes.BASE_URL_IMG}${movie.posterPath}")
                .into(binding.ivPoster)
        }
    }


}
package com.utad.rvwithmvvc.data.bookmark

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.utad.rvwithmvvc.R
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.databinding.ItemMovieBinding
import com.utad.rvwithmvvc.databinding.ItemMovieBookmarkedBinding
import com.utad.rvwithmvvc.di.Constantes
import com.utad.rvwithmvvc.domain.CheckMovieUseCase

class MovieBookmarkedViewHolder(view: View): RecyclerView.ViewHolder(view){

    val binding = ItemMovieBookmarkedBinding.bind(view)
    private lateinit var movieBookmarked: MovieBookmarked

    @SuppressLint("DefaultLocale")
    fun render(item: MovieBookmarked, onClickListener:(MovieBookmarked) -> Unit) {
        movieBookmarked = item
        binding.tvDate.text = item.releaseDate
        binding.tvTitle.text = item.title

        Glide
            .with(binding.ivPoster.context)
            // necesario añadari en el manifest android:usesCleartextTraffic="true" dentro del application
            .load("${Constantes.BASE_URL_IMG}${item.posterPath}")
            .into(binding.ivPoster)
        binding.cvMovie.setOnClickListener{
            onClickListener(item)
        }
     }

}
package com.utad.rvwithmvvc.data.modelMovie

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utad.rvwithmvvc.R
import com.utad.rvwithmvvc.databinding.ItemMovieBinding
import com.utad.rvwithmvvc.di.Constantes
import com.utad.rvwithmvvc.domain.CheckMovieUseCase
import com.utad.rvwithmvvc.domain.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){

    val binding = ItemMovieBinding.bind(view)
    private lateinit var movie: Movie

    @SuppressLint("DefaultLocale")
    fun render(item: Movie, onClickListener:(Movie) -> Unit) {
        movie = item
        Glide
            .with(binding.ivPoster.context)
            // necesario añadari en el manifest android:usesCleartextTraffic="true" dentro del application
            .load("${Constantes.BASE_URL_IMG}${item.posterPath}")
            .into(binding.ivPoster)

        // indicamos la función y si clickamos en el que cambie
        binding.ivBookmark.setOnClickListener{
            onClickListener(item)
            movie.bookmarket=!movie.bookmarket
            if(movie.bookmarket){
                binding.ivBookmark.setImageResource(R.drawable.iv_bookmark)
            } else {
                binding.ivBookmark.setImageResource(R.drawable.iv_bookempty)
            }
        }
        if(movie.bookmarket){
            binding.ivBookmark.setImageResource(R.drawable.iv_bookmark)
        } else {
            binding.ivBookmark.setImageResource(R.drawable.iv_bookempty)
        }
    }
}
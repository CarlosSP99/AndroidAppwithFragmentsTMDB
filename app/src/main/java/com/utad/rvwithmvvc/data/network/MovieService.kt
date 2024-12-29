package com.utad.rvwithmvvc.data.network

import androidx.lifecycle.LiveData
import com.utad.rvwithmvvc.data.model.PageModel
import com.utad.rvwithmvvc.di.Constantes.API_KEY
import javax.inject.Inject

class MovieService @Inject constructor(private val movieApiClient: MovieApiClient) {
    suspend fun get20Movies(page: Int): PageModel{
        return movieApiClient.getMovies20PlayingMovie(API_KEY,page)
    }

    suspend fun getNewestMovies(page: Int):PageModel{
        return movieApiClient.getNewestMoviesFromApi(API_KEY,page)
    }
}
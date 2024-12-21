package com.utad.rvwithmvvc.data.network

import com.utad.rvwithmvvc.data.model.PageModel
import com.utad.rvwithmvvc.di.Constantes.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val movieApiClient: MovieApiClient) {
    suspend fun getMovies(): PageModel{
        return movieApiClient.getMoviesAllNowPlayingMovie(API_KEY,1)
    }
}
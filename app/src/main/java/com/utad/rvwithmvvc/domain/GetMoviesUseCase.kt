package com.utad.rvwithmvvc.domain

import androidx.lifecycle.LiveData
import com.utad.rvwithmvvc.data.MovieRepository
import com.utad.rvwithmvvc.data.modelMovie.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int): List<Movie> {
        return repository.get20MoviesFromApi(page).ifEmpty { emptyList() }
    }
}
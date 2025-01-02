package com.utad.rvwithmvvc.domain

import com.utad.rvwithmvvc.data.MovieRepository
import com.utad.rvwithmvvc.data.modelMovie.Movie
import javax.inject.Inject

class GetNewestMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(page: Int): List<Movie> {
        return repository.getNewestMovies(page).ifEmpty { emptyList() }
    }
}
package com.utad.rvwithmvvc.domain

import com.utad.rvwithmvvc.data.MovieRepository
import com.utad.rvwithmvvc.data.model.MovieModel
import com.utad.rvwithmvvc.data.model.PageModel
import com.utad.rvwithmvvc.data.modelMovie.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> {
        return repository.getAllMovieFromApi().ifEmpty { emptyList() }
    }
}
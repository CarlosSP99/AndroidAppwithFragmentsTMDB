package com.utad.rvwithmvvc.domain

import com.utad.rvwithmvvc.data.MovieRepository
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarked
import com.utad.rvwithmvvc.data.modelMovie.Movie
import javax.inject.Inject

class GetAllBookmarkedMovies@Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<MovieBookmarked> {
        return repository.getBookMarkMovies().ifEmpty { emptyList() }
    }
}
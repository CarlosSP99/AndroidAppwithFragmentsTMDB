package com.utad.rvwithmvvc.domain

import com.utad.rvwithmvvc.data.MovieRepository
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.room.MovieEntity
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movieEntity: MovieEntity){
        repository.insertMovie(movieEntity)
    }
}
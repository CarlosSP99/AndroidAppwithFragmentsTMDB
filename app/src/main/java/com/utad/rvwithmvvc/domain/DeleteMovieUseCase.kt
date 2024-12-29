package com.utad.rvwithmvvc.domain

import com.utad.rvwithmvvc.data.MovieRepository
import com.utad.rvwithmvvc.data.room.MovieEntity
import javax.inject.Inject

class DeleteMovieUseCase@Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movieEntity: MovieEntity){
        repository.deleteMovie(movieEntity)
    }
}
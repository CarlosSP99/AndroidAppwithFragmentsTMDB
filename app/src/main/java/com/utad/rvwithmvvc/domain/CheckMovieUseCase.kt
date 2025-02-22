package com.utad.rvwithmvvc.domain

import android.util.Log
import com.utad.rvwithmvvc.data.MovieRepository
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.modelMovie.toDomainEntity
import com.utad.rvwithmvvc.data.room.MovieEntity
import javax.inject.Inject

    class CheckMovieUseCase@Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: Movie):Boolean{
        val result = repository.checkMovie(movie.toDomainEntity())
        if (result){
            Log.i("MovieId true","Id es trued")
            return true
        } else {
            Log.i("MovieId false","Id es false")
            return false
        }
    }
}
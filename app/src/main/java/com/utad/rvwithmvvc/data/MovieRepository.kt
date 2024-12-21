package com.utad.rvwithmvvc.data

import android.util.Log
import androidx.paging.PagingData
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarked
import com.utad.rvwithmvvc.data.dao.MovieBookmarkDao
import com.utad.rvwithmvvc.data.dao.MovieDao
import com.utad.rvwithmvvc.data.model.MovieModel
import com.utad.rvwithmvvc.data.model.PageModel
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.modelMovie.toDomainModel
import com.utad.rvwithmvvc.data.network.MovieService
import com.utad.rvwithmvvc.data.room.MovieEntity
import com.utad.rvwithmvvc.data.room.toDomainBookmark
import com.utad.rvwithmvvc.data.room.toDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieBookmarkDao: MovieBookmarkDao,
    private val movieDao: MovieDao){

    suspend fun getAllMovieFromApi(): List<Movie>{
        val listPage = movieService.getMovies()
       return listPage.results.map {
               it.toDomainModel()
        }

    }

    suspend fun insertMovie(movieEntity: MovieEntity){
       return movieDao.insertMovie(movieEntity)
    }

    suspend fun deleteMovie(movieEntity: MovieEntity){
        return movieDao.deleteMovie(movieEntity)
    }

    suspend fun checkMovie(movieEntity: MovieEntity):Boolean{
        val result = movieDao.checkMovie(movieEntity.id)
        if (result!=null){
             return true
        } else{
             return false
        }
    }

    suspend fun getBookMarkMovies():List<MovieBookmarked>{
        val result = movieBookmarkDao.getAllMoviesBookmarked()
        return result.map{
            Log.i("tagxD"," ${it.title} ")
            it.toDomainBookmark()
         }
    }
}
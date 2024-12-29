package com.utad.rvwithmvvc.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.room.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movieEntity: MovieEntity)


    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie_entity WHERE id=:id")
    suspend fun checkMovie(id: Int): MovieEntity
}
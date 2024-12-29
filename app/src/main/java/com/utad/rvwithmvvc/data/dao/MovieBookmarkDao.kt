package com.utad.rvwithmvvc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.utad.rvwithmvvc.data.room.MovieEntity

@Dao
interface MovieBookmarkDao {
    @Query("SELECT * FROM movie_entity")
    suspend fun getAllMoviesBookmarked(): List<MovieEntity>
}
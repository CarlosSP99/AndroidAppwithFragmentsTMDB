package com.utad.rvwithmvvc.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarked
import com.utad.rvwithmvvc.data.dao.MovieBookmarkDao
import com.utad.rvwithmvvc.data.dao.MovieDao

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    abstract fun getMovieBookmarkDao(): MovieBookmarkDao
}
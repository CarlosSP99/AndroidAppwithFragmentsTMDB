package com.utad.rvwithmvvc.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarked
import com.utad.rvwithmvvc.data.model.MovieModel
import com.utad.rvwithmvvc.data.modelMovie.Movie


@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey
    val id: Int=0,
    val title: String,
    val popularity: Double,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Double,
)

fun Movie.toDomainMovie() = MovieEntity(
    title =title,
    popularity = popularity,
    overview =  overview,
    releaseDate =  releaseDate,
    posterPath =  posterPath,
    voteAverage =  voteAverage,
    id = id)

fun MovieEntity.toDomainModel() = Movie(
    title=title,
    popularity =  popularity,
    overview =  overview,
    releaseDate =  releaseDate,
    posterPath =  posterPath,
    voteAverage =  voteAverage,
    id = id)

fun MovieEntity.toDomainBookmark() = MovieBookmarked(
    title=title,
    popularity =  popularity,
    overview =  overview,
    releaseDate =  releaseDate,
    posterPath =  posterPath,
    voteAverage =  voteAverage,
    id = id)

package com.utad.rvwithmvvc.data.modelMovie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarked
import com.utad.rvwithmvvc.data.model.MovieModel
import com.utad.rvwithmvvc.data.room.MovieEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
     val id: Int,
     val title: String,
     val popularity: Double,
     val overview: String,
     val releaseDate: String,
     val posterPath: String,
     val voteAverage: Double,
     var bookmarket: Boolean=false
): Parcelable

fun MovieModel.toDomainModel() =
     Movie(title=title,
     popularity =  popularity,
     overview =  overview,
     releaseDate =  releaseDate,
     posterPath =  posterPath,
     voteAverage =  voteAverage,
     id = id)

fun Movie.toDomainEntity() =
     MovieEntity(title=title,
     popularity =  popularity,
     overview =  overview,
     releaseDate =  releaseDate,
     posterPath =  posterPath,
     voteAverage =  voteAverage,
     id = id)
// para evitar problemas si se elimina la api o hay algun cambio

fun Movie.toDomainBookmarked() =
     MovieBookmarked(
          title=title,
          popularity =  popularity,
          overview =  overview,
          releaseDate =  releaseDate,
          posterPath =  posterPath,
          voteAverage =  voteAverage,
          id = id)
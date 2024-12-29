package com.utad.rvwithmvvc.data.bookmark

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieBookmarked(
    val id: Int,
    val title: String,
    val popularity: Double,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val voteAverage: Double,
    var bookmarket: Boolean=false
):Parcelable




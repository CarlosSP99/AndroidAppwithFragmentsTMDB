package com.utad.rvwithmvvc.data.network

import androidx.lifecycle.LiveData
import retrofit2.http.Query
import com.utad.rvwithmvvc.data.model.PageModel
import com.utad.rvwithmvvc.di.Constantes.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieApiClient {
    @Headers(
        "Authorization: Bearer $API_KEY",
        "accept: application/json"
    )
    @GET("now_playing?language=en-ES")
     suspend fun getMovies20PlayingMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): PageModel

    @Headers(
        "Authorization: Bearer $API_KEY",
        "accept: application/json"
    )
    @GET("top_rated?language=en-ES")
    suspend fun getNewestMoviesFromApi(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        ): PageModel


}
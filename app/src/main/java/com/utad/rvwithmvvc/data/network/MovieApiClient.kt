package com.utad.rvwithmvvc.data.network

import retrofit2.http.Query
import com.utad.rvwithmvvc.data.model.PageModel
import com.utad.rvwithmvvc.di.Constantes.API_KEY
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MovieApiClient {


    // falta el control de errores


    @Headers(
        "Authorization: Bearer $API_KEY",
        "accept: application/json"
    )
    @GET("now_playing?language=en-ES")
     suspend fun getMoviesAllNowPlayingMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): PageModel
}
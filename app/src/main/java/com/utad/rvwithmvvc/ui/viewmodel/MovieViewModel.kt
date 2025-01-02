package com.utad.rvwithmvvc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.room.toDomainMovie
import com.utad.rvwithmvvc.domain.CheckMovieUseCase
import com.utad.rvwithmvvc.domain.DeleteMovieUseCase
import com.utad.rvwithmvvc.domain.GetMoviesUseCase
import com.utad.rvwithmvvc.domain.GetNewestMoviesUseCase
import com.utad.rvwithmvvc.domain.InsertMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val checkMovieUseCase: CheckMovieUseCase,
    private val getNewestMoviesUseCase: GetNewestMoviesUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase): ViewModel(){

        private var _movieModel = MutableLiveData<List<Movie>>()
    val movieModel: LiveData<List<Movie>> = _movieModel

    private var numero = 1

    // actuará a modo de switch si está en las pelíiculas mas vistas o mejor valoradas
    private var whatIsGettingRequest:WhatIsgettingRequest = WhatIsgettingRequest.NOW_PLAYING

    private var _pageModel = MutableLiveData<Int>(numero)
    val pageModel: LiveData<Int> = _pageModel

    init{
        nowPlayingDisplay()
    }


    // para guardar las peliculas como favoritas
    fun bookmarkMovie(movie: Movie) {
        val movieEntity = movie.toDomainMovie()
        if (!movie.bookmarket){
            viewModelScope.launch (Dispatchers.IO){
                insertMovieUseCase.invoke(movieEntity)
            }
         } else {
            viewModelScope.launch (Dispatchers.IO){
                deleteMovieUseCase.invoke(movieEntity)
            }
        }
    }


    // con el switch nos aseguramos que si está en la vista de nowPlaying obtengamos las
    // siguiente págica de las mejor valoradas o viceversa
     fun nextPage(){
         numero=numero+1
         _pageModel.postValue(numero)
         viewModelScope.launch(Dispatchers.IO) {
             val result = when(whatIsGettingRequest){
                 WhatIsgettingRequest.NOW_PLAYING -> getMoviesUseCase(numero)
                 WhatIsgettingRequest.TOP_RATED -> getNewestMoviesUseCase(numero)
             }
             for (movie in result){
                 if (checkMovieUseCase(movie)){
                     movie.bookmarket=true
                 } else{
                     movie.bookmarket=false
                 }
             }
             if (result.isNotEmpty())
                 _movieModel.postValue(result)
         }
         }

    fun previousPage() {
        if (numero>1){
            numero=numero-1
            _pageModel.postValue(numero)

        }
         viewModelScope.launch(Dispatchers.IO) {
             val result = when(whatIsGettingRequest){
                 WhatIsgettingRequest.NOW_PLAYING -> getMoviesUseCase(numero)
                 WhatIsgettingRequest.TOP_RATED -> getNewestMoviesUseCase(numero)
             }
            for (movie in result){
                if (checkMovieUseCase(movie)){
                    movie.bookmarket=true
                } else{
                    movie.bookmarket=false
                }
            }
            if (result.isNotEmpty())
                _movieModel.postValue(result)
        }
    }

    // cambiar la vista
    suspend fun modifyMovieDisplays() {
        numero=1
        var result = emptyList<Movie>()

        _pageModel.postValue(numero)
            when(whatIsGettingRequest){
            WhatIsgettingRequest.NOW_PLAYING -> {
               result = getNewestMoviesUseCase(numero)
                whatIsGettingRequest = WhatIsgettingRequest.TOP_RATED
            }

            WhatIsgettingRequest.TOP_RATED -> {
                result = getMoviesUseCase(numero)
                whatIsGettingRequest = WhatIsgettingRequest.NOW_PLAYING
            }
        }
        if (result.isNotEmpty())
            _movieModel.postValue(result)
     }

     private fun nowPlayingDisplay(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = getMoviesUseCase(numero)
            for (movie in result){
                if (checkMovieUseCase(movie)){
                    movie.bookmarket=true
                } else{
                    movie.bookmarket=false
                }
            }
            if (result.isNotEmpty())
                _movieModel.postValue(result)
        }
    }

    }






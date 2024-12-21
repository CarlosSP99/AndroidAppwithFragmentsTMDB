package com.utad.rvwithmvvc.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.rvwithmvvc.data.model.MovieModel
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.room.toDomainMovie
import com.utad.rvwithmvvc.domain.CheckMovieUseCase
import com.utad.rvwithmvvc.domain.DeleteMovieUseCase
import com.utad.rvwithmvvc.domain.GetMoviesUseCase
import com.utad.rvwithmvvc.domain.InsertMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val checkMovieUseCase: CheckMovieUseCase,

    private val insertMovieUseCase: InsertMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase): ViewModel(){

    private var _movieModel = MutableLiveData<List<Movie>>()
    val movieModel: LiveData<List<Movie>> = _movieModel

    init{
        viewModelScope.launch(Dispatchers.IO) {
            val result = getMoviesUseCase()
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


}

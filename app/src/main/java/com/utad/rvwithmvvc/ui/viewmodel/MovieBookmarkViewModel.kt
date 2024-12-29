package com.utad.rvwithmvvc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarked
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.domain.GetAllBookmarkedMovies
import com.utad.rvwithmvvc.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieBookmarkViewModel @Inject constructor(
    private val getAllBookmarkedMovies: GetAllBookmarkedMovies) : ViewModel() {

    private var _movieBookmarkedModel = MutableLiveData<List<MovieBookmarked>>()
    val movieBookmarked: LiveData<List<MovieBookmarked>> = _movieBookmarkedModel


    init{
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAllBookmarkedMovies()
            if (result.isNotEmpty())
            _movieBookmarkedModel.postValue(result)
        }
    }
}
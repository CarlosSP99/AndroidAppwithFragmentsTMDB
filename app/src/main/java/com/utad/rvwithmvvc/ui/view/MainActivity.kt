package com.utad.rvwithmvvc.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.utad.rvwithmvvc.R
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.modelMovie.MovieAdapter
import com.utad.rvwithmvvc.databinding.ActivityMainBinding
import com.utad.rvwithmvvc.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        innitRv()

        observer()

        binding.btnSiguiente.setOnClickListener{
            movieViewModel.nextPage()
        }

        binding.btnAtras.setOnClickListener{
            movieViewModel.previousPage()
        }

        binding.toolbar.setOnMenuItemClickListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.bookmarkView ->{
                    val intent = Intent(this, BookmarkActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.favoriteView -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        movieViewModel.modifyMovieDisplays()
                    }
                    true
                }
                R.id.nowPlaingView -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        movieViewModel.modifyMovieDisplays()
                    }
                    true
                }

                else -> false
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observer() {
        movieViewModel.movieModel.observe(this) {movieNewList ->
            movieAdapter.submitList(movieNewList)
         }

        movieViewModel.pageModel.observe(this){
            binding.tvNumberOfPage.text=it.toString()
        }

    }

    private fun innitRv() {
        binding.rvMovie.layoutManager = GridLayoutManager(this, 2) // 2 es el número de columnas
        movieAdapter = MovieAdapter(emptyList(), onClickListener = { movieViewModel.bookmarkMovie(it)}, bookMarkMovie = { goToDetails(it)})
        binding.rvMovie.adapter = movieAdapter
     }


    private fun goToDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}
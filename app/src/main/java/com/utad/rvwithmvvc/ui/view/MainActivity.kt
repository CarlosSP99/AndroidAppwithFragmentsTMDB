package com.utad.rvwithmvvc.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.utad.rvwithmvvc.R
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.modelMovie.MovieAdapter
import com.utad.rvwithmvvc.databinding.ActivityMainBinding
import com.utad.rvwithmvvc.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        binding.toolbar.setOnMenuItemClickListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.bookmarkView ->{
                    val intent = Intent(this, BookmarkActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun observer() {
        movieViewModel.movieModel.observe(this) {movieNewList ->
            movieAdapter.submitList(movieNewList)
         }
    }

    private fun innitRv() {
        binding.rvMovie.layoutManager = GridLayoutManager(this, 2) // 2 es el número de columnas
        movieAdapter = MovieAdapter(emptyList(), onClickListener = { movieViewModel.bookmarkMovie(it)})
        binding.rvMovie.adapter = movieAdapter
     }


}
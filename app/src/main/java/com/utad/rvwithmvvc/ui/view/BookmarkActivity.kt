package com.utad.rvwithmvvc.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.utad.rvwithmvvc.R
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarked
import com.utad.rvwithmvvc.data.bookmark.MovieBookmarkedAdapter
import com.utad.rvwithmvvc.databinding.ActivityBookmarkBinding
import com.utad.rvwithmvvc.ui.viewmodel.MovieBookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding
    private lateinit var movieBookmarkAdapter: MovieBookmarkedAdapter

    private val movieBookmarkedViewModel: MovieBookmarkViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
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

    }

    private fun observer() {
        movieBookmarkedViewModel.movieBookmarked.observe(this) {movieNewList ->
            movieBookmarkAdapter.submitList(movieNewList)
        }
    }

    private fun innitRv() {
        movieBookmarkAdapter = MovieBookmarkedAdapter(emptyList(), onClickListener = { goToDetails(it) })
        binding.rvMovie.adapter = movieBookmarkAdapter
    }

    private fun goToDetails(movieBookmarked: MovieBookmarked) {
        val intent = Intent(this, MovieBookMarkedDetailsActivity::class.java)
        intent.putExtra("movie", movieBookmarked)
        startActivity(intent)
    }



}
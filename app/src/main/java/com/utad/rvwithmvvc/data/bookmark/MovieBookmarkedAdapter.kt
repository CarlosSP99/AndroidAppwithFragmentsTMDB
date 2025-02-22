package com.utad.rvwithmvvc.data.bookmark

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utad.rvwithmvvc.R
import com.utad.rvwithmvvc.data.modelMovie.Movie
import com.utad.rvwithmvvc.data.modelMovie.MovieViewHolder

class MovieBookmarkedAdapter(
    private var list: List<MovieBookmarked>,
    private  val onClickListener:(MovieBookmarked) -> Unit,
): RecyclerView.Adapter<MovieBookmarkedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieBookmarkedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieBookmarkedViewHolder(layoutInflater.inflate(R.layout.item_movie_bookmarked, parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MovieBookmarkedViewHolder, position: Int) {
        val item = list[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(movieBookmarkedList: List<MovieBookmarked>) {
        this.list = movieBookmarkedList
        notifyDataSetChanged()
    }

}
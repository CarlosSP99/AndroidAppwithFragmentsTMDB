package com.utad.rvwithmvvc.data.modelMovie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utad.rvwithmvvc.R

class MovieAdapter(private var list: List<Movie>,
                   private  val onClickListener:(Movie) -> Unit,
): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = list[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(languagesList: List<Movie>) {
        this.list = languagesList
        notifyDataSetChanged()
    }

}
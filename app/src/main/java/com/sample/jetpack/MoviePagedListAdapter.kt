package com.sample.jetpack

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MoviePagedListAdapter(val context: Context) : PagedListAdapter<Movie, MoviePagedListAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.title)
        var year: TextView = itemView.findViewById(R.id.year)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = getItem(position)
        if (movie != null) {
            Picasso.get().load(movie.image)
                .into(holder.image)

            holder.title.text = movie.title
            holder.year.text = movie.year
        } else {
            holder.image.setImageDrawable(null)
            holder.title.text = ""
            holder.year.text = ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_view_movie, parent, false)
        return MovieViewHolder(view)
    }
}
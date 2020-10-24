package com.example.bookmyshow.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.bookmyshow.R
import com.example.bookmyshow.constant.Constant
import com.example.bookmyshow.model.Movie
import kotlinx.android.synthetic.main.card_movie_item_layout.view.*

class MoviesRecyclerviewAdapter(private val moviesList: List<Movie>) :
    RecyclerView.Adapter<MoviesRecyclerviewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_movie_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Constant.IMAGE_BASE_URL + moviesList.get(position).posterPath)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (isFirstResource) {
                        holder.itemView.progressBarImageLoading.visibility = View.GONE
                    }
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.itemView.progressBarImageLoading.visibility = View.GONE
                    return false
                }
            })
            .into(holder.itemView.moviePoster)

        holder.itemView.movieTitle.text = moviesList.get(position).title
        holder.itemView.releaseDate.text = moviesList.get(position).releaseDate
        holder.itemView.avgVoting.text = moviesList.get(position).voteAverage.toString()
        holder.itemView.totalVotes.text = moviesList.get(position).voteCount.toString()

    }
}
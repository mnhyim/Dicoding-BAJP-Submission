package com.mnhyim.moviecatalog.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.databinding.ListItemCatalogBinding
import com.mnhyim.moviecatalog.ui.activities.DetailActivity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val listMovies = ArrayList<MovieResponse>()

    inner class MoviesViewHolder(private val binding: ListItemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponse) {
            with(binding) {
                tvListName.text = movie.title
                tvListDate.text = movie.releaseDate
                tvListDesc.text = movie.overview

                Glide.with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(imgListItem)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, "Movie")
                    intent.putExtra(DetailActivity.EXTRA_ITEM, movie)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun setMovies(movie: List<MovieResponse>?) {
        if (movie == null) {
            this.listMovies.clear()
            notifyDataSetChanged()

        } else {
            this.listMovies.addAll(movie)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            ListItemCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size
}
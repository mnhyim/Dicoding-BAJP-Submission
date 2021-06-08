package com.mnhyim.moviecatalog.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse
import com.mnhyim.moviecatalog.databinding.ListItemCatalogBinding
import com.mnhyim.moviecatalog.ui.activities.DetailActivity

class ShowsAdapter : RecyclerView.Adapter<ShowsAdapter.ShowsViewHolder>() {

    private val listShows = ArrayList<ShowResponse>()

    inner class ShowsViewHolder(private val binding: ListItemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(show: ShowResponse) {
            with(binding) {
                tvListName.text = show.name
                tvListDate.text = show.firstAirDate
                tvListDesc.text = show.overview

                Glide.with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/w500/${show.posterPath}")
                    .into(imgListItem)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, "TV Show")
                    intent.putExtra(DetailActivity.EXTRA_ITEM, show)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun setShows(show: List<ShowResponse>?) {
        if (show == null) return this.listShows.clear() else this.listShows.addAll(show)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsViewHolder {
        val binding =
            ListItemCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        val shows = listShows[position]
        holder.bind(shows)
    }

    override fun getItemCount(): Int = listShows.size
}
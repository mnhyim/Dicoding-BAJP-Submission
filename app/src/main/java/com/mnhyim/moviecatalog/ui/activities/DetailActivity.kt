package com.mnhyim.moviecatalog.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mnhyim.moviecatalog.R
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse
import com.mnhyim.moviecatalog.databinding.ActivityDetailBinding
import com.mnhyim.moviecatalog.ui.viewmodels.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private val TAG: String = this::class.java.simpleName

    companion object {
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (intent.getStringExtra("extra_type")) {
            "Movie" -> {
                val movie = intent.getParcelableExtra<MovieResponse>(EXTRA_ITEM) as MovieResponse

                Log.d(TAG, "${movie.title} item")
                binding.progressBar.visibility = View.GONE
                populateMovieDetail(movie)

                detailViewModel.getMovieById(movie.id).observe(this, { data ->
                    if (data > 0) {
                        binding.fabFavorite.setImageResource(R.drawable.ic_remove)
                        binding.fabFavorite.setOnClickListener {
                            detailViewModel.deleteFavoriteMovie(movie)
                            binding.fabFavorite.setImageResource(R.drawable.ic_add)
                            Toast.makeText(this, "Movie removed succesfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        binding.fabFavorite.setImageResource(R.drawable.ic_add)
                        binding.fabFavorite.setOnClickListener {
                            detailViewModel.addFavoriteMovie(movie)
                            binding.fabFavorite.setImageResource(R.drawable.ic_remove)
                            Toast.makeText(this, "Movie added succesfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
            }
            "TV Show" -> {
                val show = intent.getParcelableExtra<ShowResponse>(EXTRA_ITEM) as ShowResponse

                Log.d(TAG, "${show.name} item")
                binding.progressBar.visibility = View.GONE
                populateShowDetail(show)

                detailViewModel.getShowById(show.id).observe(this, { data ->
                    if (data > 0) {
                        binding.fabFavorite.setImageResource(R.drawable.ic_remove)
                        binding.fabFavorite.setOnClickListener {
                            detailViewModel.deleteFavoriteShow(show)
                            binding.fabFavorite.setImageResource(R.drawable.ic_add)
                            Toast.makeText(this, "Show removed succesfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        binding.fabFavorite.setImageResource(R.drawable.ic_add)
                        binding.fabFavorite.setOnClickListener {
                            detailViewModel.addFavoriteShow(show)
                            binding.fabFavorite.setImageResource(R.drawable.ic_remove)
                            Toast.makeText(this, "Show added succesfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun populateMovieDetail(movie: MovieResponse) {
        title = movie.title

        with(binding) {
            tvDetailName.text = movie.title
            tvDetailRelease.text = movie.releaseDate
            tvDetailLanguage.text = movie.originalLanguage.toString().uppercase()
            tvDetailScore.text = movie.voteAverage.toString()
            tvDetailDesc.text = movie.overview
            Glide.with(baseContext)
                .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                .into(imgDetailPoster)

            Glide.with(baseContext)
                .load("https://image.tmdb.org/t/p/original/${movie.backdropPath}")
                .apply(RequestOptions().override(350, 350))
                .into(imgBg)

        }
    }

    private fun populateShowDetail(show: ShowResponse) {
        title = show.name

        with(binding) {
            tvDetailName.text = show.name
            tvLabelRelease.text = getString(R.string.label_release_2)
            tvDetailRelease.text = show.firstAirDate
            tvLabelLanguage.text = getString(R.string.label_country)
            tvDetailLanguage.text = show.originCountry.joinToString(",")
            tvDetailScore.text = show.voteAverage.toString()
            tvDetailDesc.text = show.overview
            Glide.with(baseContext)
                .load("https://image.tmdb.org/t/p/w500/${show.posterPath}")
                .into(imgDetailPoster)

            Glide.with(baseContext)
                .load("https://image.tmdb.org/t/p/original/${show.backdropPath}")
                .apply(RequestOptions().override(350, 350))
                .into(imgBg)
        }
    }
}
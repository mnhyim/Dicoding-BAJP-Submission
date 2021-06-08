package com.mnhyim.moviecatalog.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnhyim.moviecatalog.databinding.FragmentMoviesBinding
import com.mnhyim.moviecatalog.ui.adapters.MoviesAdapter
import com.mnhyim.moviecatalog.ui.viewmodels.MoviesViewModel
import com.mnhyim.moviecatalog.utils.DataMapper.mapMovieEntitiesToResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment(private val favoriteScreen: Boolean = false) : Fragment() {

    private val TAG: String = this::class.java.simpleName
    private val moviesViewModel: MoviesViewModel by viewModel()
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

        val moviesAdapter = MoviesAdapter()

        if (!favoriteScreen) {
            moviesViewModel.discoverMovies().observe(viewLifecycleOwner, { data ->
                if (data.isEmpty()) {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmptyMovies.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmptyMovies.visibility = View.INVISIBLE
                }

                Log.d(TAG, "data rv: ${data}")

                moviesAdapter.setMovies(data)
                with(binding.rvMovies) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = moviesAdapter
                }

                Log.d(TAG, "adapterItemCount: ${moviesAdapter.itemCount}")
            })
        } else {
            moviesViewModel.getAllFavorites().observe(viewLifecycleOwner, { data ->
                if (data.isEmpty()) {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmptyMovies.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmptyMovies.visibility = View.INVISIBLE
                }

                Log.d(TAG, "data rv: ${data}")

                moviesAdapter.setMovies(mapMovieEntitiesToResponse(data))
                with(binding.rvMovies) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = moviesAdapter
                }

                Log.d(TAG, "adapterItemCount: ${moviesAdapter.itemCount}")
            })
        }
    }
}
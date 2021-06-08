package com.mnhyim.moviecatalog.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnhyim.moviecatalog.databinding.FragmentShowsBinding
import com.mnhyim.moviecatalog.ui.adapters.ShowsAdapter
import com.mnhyim.moviecatalog.ui.viewmodels.ShowsViewModel
import com.mnhyim.moviecatalog.utils.DataMapper
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowsFragment(private val favoriteScreen: Boolean = false) : Fragment() {

    private val TAG: String = this::class.java.simpleName
    private val showViewModel: ShowsViewModel by viewModel()
    private lateinit var binding: FragmentShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")
        binding = FragmentShowsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        val showsAdapter = ShowsAdapter()

        if (!favoriteScreen) {
            showViewModel.discoverShows().observe(viewLifecycleOwner, { data ->
                if (data.isEmpty()) {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmptyShows.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmptyShows.visibility = View.INVISIBLE
                }

                showsAdapter.setShows(data)
                with(binding.rvShows) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = showsAdapter
                }

                Log.d(TAG, "adapterItemCount: ${showsAdapter.itemCount}")
            })
        } else {
            showViewModel.getAllFavorites().observe(viewLifecycleOwner, { data ->
                if (data.isEmpty()) {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmptyShows.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.tvEmptyShows.visibility = View.INVISIBLE
                }
                Log.d(TAG, "data rv: ${data}")

                showsAdapter.setShows(DataMapper.mapShowEntitiesToResponse(data))
                with(binding.rvShows) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = showsAdapter
                }

                Log.d(TAG, "adapterItemCount: ${showsAdapter.itemCount}")
            })
        }
    }
}
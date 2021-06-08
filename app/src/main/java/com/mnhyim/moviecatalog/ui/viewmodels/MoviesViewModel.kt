package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mnhyim.moviecatalog.data.CatalogRepository
import com.mnhyim.moviecatalog.data.local.entity.MovieEntity
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse

class MoviesViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun discoverMovies(): LiveData<List<MovieResponse>> = catalogRepository.discoverMovies()

    fun getAllFavorites(): LiveData<PagedList<MovieEntity>> = catalogRepository.getAllMovies()
}

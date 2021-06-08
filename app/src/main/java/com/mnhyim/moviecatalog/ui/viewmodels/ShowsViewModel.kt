package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mnhyim.moviecatalog.data.CatalogRepository
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse

class ShowsViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun discoverShows(): LiveData<List<ShowResponse>> = catalogRepository.discoverShows()

    fun getAllFavorites(): LiveData<PagedList<ShowEntity>> = catalogRepository.getAllShows()
}
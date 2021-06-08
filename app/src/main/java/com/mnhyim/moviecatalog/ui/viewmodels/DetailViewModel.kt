package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mnhyim.moviecatalog.data.CatalogRepository
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse
import com.mnhyim.moviecatalog.utils.DataMapper

class DetailViewModel(private val catalogRepository: CatalogRepository) : ViewModel() {

    fun getMovieById(id: Int): LiveData<Int> = catalogRepository.getMovieById(id)

    fun getShowById(id: Int): LiveData<Int> = catalogRepository.getShowById(id)

    fun addFavoriteMovie(movie: MovieResponse) {
        catalogRepository.insertMovie(DataMapper.mapMovieResponseToEntity(movie))
    }

    fun addFavoriteShow(show: ShowResponse) {
        catalogRepository.insertShow(DataMapper.mapShowResponseToEntity(show))
    }

    fun deleteFavoriteMovie(movie: MovieResponse) {
        catalogRepository.deleteMovie(DataMapper.mapMovieResponseToEntity(movie))
    }

    fun deleteFavoriteShow(show: ShowResponse) {
        catalogRepository.deleteShow(DataMapper.mapShowResponseToEntity(show))
    }
}
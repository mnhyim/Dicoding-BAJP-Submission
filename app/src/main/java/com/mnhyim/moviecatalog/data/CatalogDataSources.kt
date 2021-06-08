package com.mnhyim.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mnhyim.moviecatalog.data.local.entity.MovieEntity
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse

interface CatalogDataSources {

    fun discoverMovies(): LiveData<List<MovieResponse>>

    fun discoverShows(): LiveData<List<ShowResponse>>

    fun insertMovie(movie: MovieEntity)

    fun deleteMovie(movie: MovieEntity)

    fun getAllMovies(): LiveData<PagedList<MovieEntity>>

    fun getMovieById(id: Int): LiveData<Int>

    fun insertShow(show: ShowEntity)

    fun deleteShow(show: ShowEntity)

    fun getAllShows(): LiveData<PagedList<ShowEntity>>

    fun getShowById(id: Int): LiveData<Int>
}
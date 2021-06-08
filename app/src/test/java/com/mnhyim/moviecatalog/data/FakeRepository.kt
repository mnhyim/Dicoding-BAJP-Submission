package com.mnhyim.moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mnhyim.moviecatalog.data.local.LocalDataSource
import com.mnhyim.moviecatalog.data.local.entity.MovieEntity
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity
import com.mnhyim.moviecatalog.data.remote.DiscoverMoviesCallback
import com.mnhyim.moviecatalog.data.remote.DiscoverShowsCallback
import com.mnhyim.moviecatalog.data.remote.RemoteDataSource
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse

class FakeRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CatalogDataSources {

    private val TAG: String = CatalogRepository::class.java.simpleName
    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .build()


    override fun discoverMovies(): LiveData<List<MovieResponse>> {
        val listMovies = MutableLiveData<List<MovieResponse>>()

        remoteDataSource.discoverMovies(object : DiscoverMoviesCallback {
            override fun onDiscoverMoviesReceived(discoverResponse: List<MovieResponse>) {
                listMovies.postValue(discoverResponse)
            }
        })

        return listMovies
    }

    override fun discoverShows(): LiveData<List<ShowResponse>> {
        val listShows = MutableLiveData<List<ShowResponse>>()

        remoteDataSource.discoverShows(object : DiscoverShowsCallback {
            override fun onDiscoverShowsReceived(discoverResponse: List<ShowResponse>) {
                listShows.postValue(discoverResponse)
            }
        })

        return listShows
    }

    override fun insertMovie(movie: MovieEntity) {
        localDataSource.insertMovie(movie)
    }

    override fun deleteMovie(movie: MovieEntity) {
        localDataSource.deleteMovie(movie)
    }

    override fun getAllMovies(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
    }

    override fun getMovieById(id: Int): LiveData<Int> {
        return localDataSource.getMovieById(id)
    }

    override fun insertShow(show: ShowEntity) {
        localDataSource.insertShow(show)
    }

    override fun deleteShow(show: ShowEntity) {
        localDataSource.deleteShow(show)
    }

    override fun getAllShows(): LiveData<PagedList<ShowEntity>> {
        return LivePagedListBuilder(localDataSource.getAllShows(), config).build()
    }

    override fun getShowById(id: Int): LiveData<Int> {
        return localDataSource.getShowById(id)
    }
}
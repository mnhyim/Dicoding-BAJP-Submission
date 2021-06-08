package com.mnhyim.moviecatalog.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.mnhyim.moviecatalog.data.local.entity.MovieEntity
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity
import com.mnhyim.moviecatalog.data.local.room.MovieDao
import com.mnhyim.moviecatalog.data.local.room.ShowDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource(private val movieDao: MovieDao, private val showDao: ShowDao) {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    // Movie
    fun insertMovie(movie: MovieEntity) {
        executorService.execute {
            movieDao.insert(movie)
        }
    }

    fun deleteMovie(movie: MovieEntity) {
        executorService.execute {
            movieDao.delete(movie)
        }
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getAllMovies()
    }

    fun getMovieById(id: Int): LiveData<Int> {
        return movieDao.getMovieById(id)
    }

    // Show
    fun insertShow(show: ShowEntity) {
        executorService.execute {
            showDao.insert(show)
        }
    }

    fun deleteShow(show: ShowEntity) {
        executorService.execute {
            showDao.delete(show)
        }
    }

    fun getAllShows(): DataSource.Factory<Int, ShowEntity> {
        return showDao.getAllShows()
    }

    fun getShowById(id: Int): LiveData<Int> {
        return showDao.getShowById(id)
    }
}
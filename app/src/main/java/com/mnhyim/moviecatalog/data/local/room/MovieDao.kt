package com.mnhyim.moviecatalog.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mnhyim.moviecatalog.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)

    @Query("SELECT * FROM movie ORDER BY title ASC")
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT COUNT() FROM movie WHERE id = :id")
    fun getMovieById(id: Int): LiveData<Int>
}
package com.mnhyim.moviecatalog.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity

@Dao
interface ShowDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: ShowEntity)

    @Delete
    fun delete(movie: ShowEntity)

    @Query("SELECT * FROM show ORDER BY name ASC")
    fun getAllShows(): DataSource.Factory<Int, ShowEntity>

    @Query("SELECT COUNT() FROM show WHERE id = :id")
    fun getShowById(id: Int): LiveData<Int>
}
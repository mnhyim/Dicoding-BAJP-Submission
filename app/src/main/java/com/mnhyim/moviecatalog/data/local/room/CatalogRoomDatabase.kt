package com.mnhyim.moviecatalog.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mnhyim.moviecatalog.data.local.entity.MovieEntity
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity
import com.mnhyim.moviecatalog.utils.DataConverters

@Database(entities = [MovieEntity::class, ShowEntity::class], version = 1)
@TypeConverters(DataConverters::class)
abstract class CatalogRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun showDao(): ShowDao
}
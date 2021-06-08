package com.mnhyim.moviecatalog.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.mnhyim.moviecatalog.data.local.LocalDataSource
import com.mnhyim.moviecatalog.data.local.entity.MovieEntity
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity
import com.mnhyim.moviecatalog.data.remote.DiscoverMoviesCallback
import com.mnhyim.moviecatalog.data.remote.DiscoverShowsCallback
import com.mnhyim.moviecatalog.data.remote.RemoteDataSource
import com.mnhyim.moviecatalog.utils.DummyData
import com.mnhyim.moviecatalog.utils.LiveDataTestUtil
import com.mnhyim.moviecatalog.utils.PagedListUtil.mockPagedList
import com.mnhyim.moviecatalog.utils.TestHelper.anyOfT
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@Suppress("UNCHECKED_CAST")
class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)

    private val repository = FakeRepository(remoteDataSource, localDataSource)

    private val moviesData = DummyData.dummyListMovies()
    private val showsData = DummyData.dummyListShows()

    @Test
    fun discoverMoviesTest() {
        doAnswer {
            (it.arguments[0] as DiscoverMoviesCallback).onDiscoverMoviesReceived(moviesData)
        }.`when`(remoteDataSource).discoverMovies(anyOfT(DiscoverMoviesCallback::class.java))

        val discoverMoviesResult = LiveDataTestUtil.getValue(repository.discoverMovies())
        verify(remoteDataSource).discoverMovies(anyOfT(DiscoverMoviesCallback::class.java))
        assertNotNull(discoverMoviesResult)
        assertEquals(discoverMoviesResult.size, moviesData.size)
    }

    @Test
    fun discoverShowsTest() {
        doAnswer {
            (it.arguments[0] as DiscoverShowsCallback).onDiscoverShowsReceived(showsData)
        }.`when`(remoteDataSource).discoverShows(anyOfT(DiscoverShowsCallback::class.java))

        val discoverShowsResult = LiveDataTestUtil.getValue(repository.discoverShows())
        verify(remoteDataSource).discoverShows(anyOfT(DiscoverShowsCallback::class.java))
        assertNotNull(discoverShowsResult)
        assertEquals(discoverShowsResult.size, showsData.size)
    }

    @Test
    fun favoritesMoviesTest() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getAllMovies()).thenReturn(dataSourceFactory)
        repository.getAllMovies()

        val movies = mockPagedList(DummyData.dummyListMovies())
        verify(localDataSource).getAllMovies()
        assertNotNull(movies)
        assertEquals(movies.size, moviesData.size)
    }

    @Test
    fun favoritesShowsTest() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ShowEntity>
        `when`(localDataSource.getAllShows()).thenReturn(dataSourceFactory)
        repository.getAllShows()

        val shows = mockPagedList(DummyData.dummyListShows())
        verify(localDataSource).getAllShows()
        assertNotNull(shows)
        assertEquals(shows.size, showsData.size)
    }
}
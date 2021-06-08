package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mnhyim.moviecatalog.data.CatalogRepository
import com.mnhyim.moviecatalog.data.local.entity.MovieEntity
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.utils.DummyData
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Mock
    private lateinit var favMovieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observer: Observer<List<MovieResponse>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(catalogRepository)
    }

    @Test
    fun discoverMoviesTest() {
        val moviesDummy = DummyData.dummyListMovies()
        val moviesLiveDataDummy = MutableLiveData<List<MovieResponse>>()
        moviesLiveDataDummy.value = moviesDummy

        `when`(catalogRepository.discoverMovies()).thenReturn(moviesLiveDataDummy)
        val moviesData = viewModel.discoverMovies().value
        verify(catalogRepository).discoverMovies()

        assertNotNull(moviesData)
        assertEquals(moviesData?.size, moviesDummy.size)

        viewModel.discoverMovies().observeForever(observer)
        verify(observer).onChanged(moviesDummy)
    }

    @Test
    fun getFavoriteMoviesTest() {
        val moviesDummy = pagedListMovie
        `when`(moviesDummy.size).thenReturn(10)
        val moviesDataLiveMock = MutableLiveData<PagedList<MovieEntity>>()
        moviesDataLiveMock.value = moviesDummy

        `when`(catalogRepository.getAllMovies()).thenReturn(moviesDataLiveMock)
        val moviesData = viewModel.getAllFavorites().value
        verify(catalogRepository).getAllMovies()
        assertNotNull(moviesData)
        assertEquals(10, moviesData?.size)

        viewModel.getAllFavorites().observeForever(favMovieObserver)
        verify(favMovieObserver).onChanged(moviesDummy)
    }
}
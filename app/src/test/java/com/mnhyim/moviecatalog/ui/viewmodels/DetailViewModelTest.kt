package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mnhyim.moviecatalog.data.CatalogRepository
import com.mnhyim.moviecatalog.utils.DummyData
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var thrown = ExpectedException.none()

    private var repository = mock(CatalogRepository::class.java)

    @Mock
    private lateinit var favObserver: Observer<Int>

    private val idTestMovie = 460465
    private val idTestShow = 60735

    @Before
    fun init() {
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun checkFavoriteMovieTest() {
        val movieDataById = listOf(DummyData.dummyListMovies().find { it.id == idTestMovie })

        val movieByIdCount = MutableLiveData<Int>()
        movieByIdCount.value = movieDataById.size

        Mockito.`when`(repository.getMovieById(idTestMovie)).thenReturn(movieByIdCount)
        viewModel.getMovieById(idTestMovie).observeForever(favObserver)

        verify(favObserver).onChanged(movieDataById.size)
    }

    @Test
    fun checkFavoriteShowTest() {
        val showDataById = listOf(DummyData.dummyListShows().find { it.id == idTestShow })

        val showByIdCount = MutableLiveData<Int>()
        showByIdCount.value = showDataById.size

        Mockito.`when`(repository.getShowById(idTestShow)).thenReturn(showByIdCount)
        viewModel.getShowById(idTestShow).observeForever(favObserver)

        verify(favObserver).onChanged(showDataById.size)
    }
}
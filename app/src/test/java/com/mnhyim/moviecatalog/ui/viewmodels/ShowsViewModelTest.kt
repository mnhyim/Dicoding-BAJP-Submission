package com.mnhyim.moviecatalog.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mnhyim.moviecatalog.data.CatalogRepository
import com.mnhyim.moviecatalog.data.local.entity.ShowEntity
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse
import com.mnhyim.moviecatalog.utils.DummyData
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowsViewModelTest {

    private lateinit var viewModel: ShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var pagedListShow: PagedList<ShowEntity>

    @Mock
    private lateinit var favShowObserver: Observer<PagedList<ShowEntity>>

    @Mock
    private lateinit var observer: Observer<List<ShowResponse>>

    @Before
    fun setUp() {
        viewModel = ShowsViewModel(catalogRepository)
    }

    @Test
    fun discoverShowsTest() {
        val showsDummy = DummyData.dummyListShows()
        val showsLiveDataDummy = MutableLiveData<List<ShowResponse>>()
        showsLiveDataDummy.value = showsDummy

        `when`(catalogRepository.discoverShows()).thenReturn(showsLiveDataDummy)
        val showsData = viewModel.discoverShows().value
        verify(catalogRepository).discoverShows()

        assertNotNull(showsData)
        assertEquals(showsData?.size, showsDummy.size)

        viewModel.discoverShows().observeForever(observer)
        verify(observer).onChanged(showsDummy)
    }

    @Test
    fun getFavoriteMoviesTest() {
        val showsDummy = pagedListShow
        `when`(showsDummy.size).thenReturn(10)
        val showsDataLiveMock = MutableLiveData<PagedList<ShowEntity>>()
        showsDataLiveMock.value = showsDummy

        `when`(catalogRepository.getAllShows()).thenReturn(showsDataLiveMock)
        val showsData = viewModel.getAllFavorites().value
        verify(catalogRepository).getAllShows()
        assertNotNull(showsData)
        assertEquals(10, showsData?.size)

        viewModel.getAllFavorites().observeForever(favShowObserver)
        verify(favShowObserver).onChanged(showsDummy)
    }
}
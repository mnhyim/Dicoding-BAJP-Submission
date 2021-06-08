package com.mnhyim.moviecatalog.data.remote

import android.util.Log
import com.mnhyim.moviecatalog.BuildConfig.THEMOVIEDB_TOKEN
import com.mnhyim.moviecatalog.data.remote.api.ApiService
import com.mnhyim.moviecatalog.data.remote.response.MovieResponse
import com.mnhyim.moviecatalog.data.remote.response.MoviesDiscoverResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowsDiscoverResponse
import com.mnhyim.moviecatalog.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    private var TAG: String = this::class.java.simpleName

    fun discoverMovies(callback: DiscoverMoviesCallback) {
        EspressoIdlingResource.increment()

        apiService.discoverMovies(THEMOVIEDB_TOKEN, 1)
            .enqueue(object : Callback<MoviesDiscoverResponse> {
                override fun onResponse(
                    call: Call<MoviesDiscoverResponse>,
                    response: Response<MoviesDiscoverResponse>
                ) {
                    response.body()?.let { callback.onDiscoverMoviesReceived(it.results) }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<MoviesDiscoverResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message.toString()}")
                }
            })
    }

    fun discoverShows(callback: DiscoverShowsCallback) {
        EspressoIdlingResource.increment()
        apiService.discoverShows(THEMOVIEDB_TOKEN, 1)
            .enqueue(object : Callback<ShowsDiscoverResponse> {
                override fun onResponse(
                    call: Call<ShowsDiscoverResponse>,
                    response: Response<ShowsDiscoverResponse>
                ) {
                    response.body()?.let { callback.onDiscoverShowsReceived(it.results) }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ShowsDiscoverResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message.toString()}")
                }
            })
    }
}

interface DiscoverMoviesCallback {
    fun onDiscoverMoviesReceived(discoverResponse: List<MovieResponse>)
}

interface DiscoverShowsCallback {
    fun onDiscoverShowsReceived(discoverResponse: List<ShowResponse>)
}
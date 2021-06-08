package com.mnhyim.moviecatalog.data.remote.api

import com.mnhyim.moviecatalog.data.remote.response.MoviesDiscoverResponse
import com.mnhyim.moviecatalog.data.remote.response.ShowsDiscoverResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Call<MoviesDiscoverResponse>

    @GET("discover/tv")
    fun discoverShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Call<ShowsDiscoverResponse>
}